'use strict';

RM.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider.when('/login', {
				templateUrl : 'views/memberLogin.html',
				controller : 'MemberLoginController',
				publicPage : true
			}).when('/memberProfile', {
				templateUrl : 'views/memberProfile.html'
			}).when('/signup', {
				templateUrl : 'views/signup.html',
				controller : 'SignupController',
				publicPage : true
			}).when('/addFlat', {
				templateUrl : 'views/addFlat.html',
				controller : 'FlatController',
				publicPage : true
			});
		} ]);