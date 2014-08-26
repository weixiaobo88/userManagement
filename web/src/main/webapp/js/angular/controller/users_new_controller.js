angular.module('userManagement')
    .controller('UsersNewController', function ($scope, $resource, $location) {
        var User = $resource("/web/api/v1/users/");
        $scope.user = {};
        $scope.create = function(){
            var localUser = new User($scope.user);
            localUser.$save().then(function(result){
                $location.path("/");
            });

        }
    });