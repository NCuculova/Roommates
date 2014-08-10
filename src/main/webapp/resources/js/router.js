'use strict';

RM.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider.when('/login', {
				templateUrl : 'views/memberLogin.html',
				controller : 'MemberLoginController',
				publicPage : true
			}).when('/memberProfile', {
				templateUrl : 'views/memberProfile.html',
				controller : 'MemberProfileController'
			}).when('/signup', {
				templateUrl : 'views/signup.html',
				controller : 'SignupController',
				publicPage : true
			});
		} ]);