'use strict';

/**
 * @ngdoc function
 * @name partyBidApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the partyBidApp
 */
angular.module('userManagement')
    .controller('UsersController', function ($scope, $resource,$http) {

        var User = $resource("/web/api/v1/users/:userId");
        $scope.users = User.query();
        $scope.selected_users =[];

        $scope.deleteUser = function(userId){
            User.delete({userId:userId}, function(){
                clear_user_in_scope(userId);
            });
        };

        $scope.delete_all_selected_users = function(){
            $http.delete("/web/api/v1/users/:batch", {
                data:$scope.selected_users,
                headers:{'Content-Type':'application/json'}
            }).success(function(){
                _($scope.selected_users).each(function(userId){
                    clear_user_in_scope(userId);
                });
                $scope.selected_users = [];
            });
        };

        function clear_user_in_scope(userId){
            $scope.users =_($scope.users).reject(function(user){
                return user.id == userId;
            });
        };


    });