'use strict';

RM.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider.when('/login', {
				templateUrl : 'views/memberLogin.html',
				controller : 'MemberLoginController',
				//public pages
				publicPage : true
			}).when('/memberProfile', {//not a public page, member must be signed in
				templateUrl : 'views/memberProfile.html',
				controller : 'MemberProfileController'
			}).when('/signup', {
				templateUrl : 'views/signup.html',
				controller : 'SignupController',
				publicPage : true
			}).when('/listings', {
				templateUrl : 'views/listings.html',
				controller : 'ListingController',
				publicPage : true
			}).when('/addListing', {
				templateUrl : 'views/addListing.html',
				controller : 'ListingController',
				publicPage : true
			}).when('/addFlat', {
				templateUrl : 'views/addFlat.html',
				controller : 'FlatController'
			}).when('/myFlat', {
				templateUrl : 'views/myFlat.html',
				controller : 'MyFlatController'
			}).when('/editFlat', {
				templateUrl : 'views/editFlat.html',
				controller : 'MyFlatController'
				//publicPage : true //---> i za vaa strana treba da bidish najaven, inaku ke bide public, taka da ne treba voa nasekade :)
			}).otherwise({redirectTo: '/'});
		} ]);
