'use strict';

/**
 * @ngdoc function
 * @name partyBidApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the partyBidApp
 */
angular.module('userManagement')
    .controller('UsersController', function ($scope, $resource) {

        $scope.users = $resource("/web/user/all").query();

    });