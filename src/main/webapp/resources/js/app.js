'use strict';

// Declare app level module which depends on filters, and services
var RM = angular.module('RM', [ 'RM.services', 'RM.directives', 'RM.dependencies' ]);

RM.config([
		'$routeProvider',
		'$httpProvider',
		'$locationProvider',
		function($routeProvider, $httpProvider,
				$locationProvider) {
		} ]);

RM.run(function($rootScope, $location) {

});
