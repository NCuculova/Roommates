'use strict';

RM.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider.when('/', {
				templateUrl : 'views/index.html',
				publicPage : true
			}).when('/login', {
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
				controller : 'ListingController'
			}).when('/allListings', {
				controller : 'AllListingsController',
				templateUrl : 'views/allListings.html',
				publicPage : true
			}).when('/addFlat', {
				templateUrl : 'views/addFlat.html',
				controller : 'FlatController'
			}).when('/bookmarks', {
				templateUrl : 'views/likedListings.html',
				controller : 'BookmarkListingController'
			}).otherwise({redirectTo: '/'});
		} ]);
