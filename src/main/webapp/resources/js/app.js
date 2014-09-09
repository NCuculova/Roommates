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

RM.run(function($rootScope, $location, $cookieStore,toaster, Member) {
	$rootScope.logout = function (){
		//delete cookie
		$cookieStore.remove("token");
		//delete member
		delete $rootScope.member;
		//redirect
		$location.path('/');
		toaster.pop('success', "You are signed out!");
	};
	var publicPages = ['/', '/allListings', '/signup', '/login'];
	var validateToken = function() {
		var token = $cookieStore.get("token");
		if(token) {
			Member.auth({
				token: token
			},function(data){
				// Member has valid cookie
				if(data.success) {
					$rootScope.member = data.member;
					//dispatch the event 'memberLoaded' to all child scopes
					$rootScope.$broadcast('memberLoaded');
				} else {
					$location.path('/login');
				}
			});		
		} else {
			$location.path('/login');
		}
	};
	//if page is not public validateToken
	if(publicPages.indexOf($location.path()) == -1) {
		validateToken();
	}
	//check pages
	$rootScope.$on('$routeChangeStart', function(event, next, current){
		console.log(next);
		if (next && !next.publicPage){
			validateToken();
		}
	});

});
