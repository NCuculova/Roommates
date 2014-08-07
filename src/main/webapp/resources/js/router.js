'use strict';

RM.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider.when('/members', {
				templateUrl : 'views/member.html',
				controller : 'MembersController'
			}).when('/memberProfile', {
				templateUrl : 'views/memberProfile.html'
			});
		} ]);
