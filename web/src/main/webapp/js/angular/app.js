'use strict';

/**
 * @ngdoc overview
 * @name partyBidApp
 * @description
 * # partyBidApp
 *
 * Main module of the application.
 */
angular
    .module('userManagement', [
        'ngResource',
        'ngRoute',
        'checklist-model'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/users.html',
                controller: 'UsersController'
            })
            .when('/new', {
                templateUrl: 'views/new.html',
                controller: 'UsersNewController'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
