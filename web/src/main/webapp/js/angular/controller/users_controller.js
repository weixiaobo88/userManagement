'use strict';

/**
 * @ngdoc function
 * @name partyBidApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the partyBidApp
 */
angular.module('userManagement')
    .controller('UsersController', function ($scope, $location, $resource,$http) {

        var User = $resource("/web/api/v1/users/:userId");
        $scope.users = User.query();

        $scope.deleteUser = function(userId){
            User.delete({userId:userId}, function(){
                clear_user_in_scope(userId);
            });
        };

        $scope.delete_all_selected_users = function(){
            var selected_users = _.chain($scope.users).where({selected:true}).pluck('id').value();
            $http.delete("/web/api/v1/users/:batch", {
                data:selected_users,
                headers:{'Content-Type':'application/json'}
            }).success(function(){
                _(selected_users).each(function(userId){
                    clear_user_in_scope(userId);
                });
            });
        };

        function clear_user_in_scope(userId){
            $scope.users =_($scope.users).reject(function(user){
                return user.id == userId;
            });
        };

        $scope.selectedAll = false;

        $scope.select_all_or_select_none = function(){
            if ($scope.selectedAll) {
                $scope.selectedAll = true;
            } else {
                $scope.selectedAll = false;
            }
            _($scope.users).each(function (user) {
                user.selected = $scope.selectedAll;
            });
        }

        $scope.go_to_create_user = function(){
            $location.path("/new");
        }

    });