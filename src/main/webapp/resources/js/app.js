'use strict';


/**
 * Declare app level module which depends on filters and services
 */ 
var RM = angular.module('RM', [ 'RM.services', 'RM.directives',
		'RM.dependencies' ]);


RM.config([ '$routeProvider', '$httpProvider', '$locationProvider',
		function($routeProvider, $httpProvider, $locationProvider) {
		} ]);


/**
 * Function that is executed on every loading on a page.
 */
RM.run(function($rootScope, $location, $cookieStore, toaster, Member) {
	//function for log out
	$rootScope.logout = function() {
		// delete cookie
		$cookieStore.remove("token");
		// delete member
		delete $rootScope.member;
		// redirect
		$location.path('/');
		toaster.pop('success', "You are signed out!");
	};
	var publicPages = [ '/', '/allListings', '/signup', '/login' ];

	//GetMember function which authenticates the member and puts in on $rootScope
	$rootScope.getMember = function(callback) {
		var token = $cookieStore.get("token");
		console.log(token);
		if (token) {
			Member.auth({
				token : token
			}, function(data) {
				// Member has valid cookie
				$rootScope.member = data.member;
				if (callback && typeof callback === 'function')
					callback(data);
			});
		}
		else{
			var data = {};
			data.success = false;
			if (callback && typeof callback === 'function')
				callback(data);
		}
	};
	//Function for validating the token
	var validateToken = function() {
		var token = $cookieStore.get("token");
		if (token) {
			Member.auth({
				token : token
			}, function(data) {
				// Member has valid cookie
				if (data.success) {
					$rootScope.member = data.member;
				} else {
					$location.path('/login');
				}
			});
		} else {
			$location.path('/login');
		}
	};
	// if page is not public validateToken
	if (publicPages.indexOf($location.path()) == -1) {
		validateToken();
	}
	// check pages on navigation and reevaluates token
	$rootScope.$on('$routeChangeStart', function(event, next, current) {
		console.log(next);
		if (next && !next.publicPage) {
			validateToken();
		}
	});

});
