'use strict';

/* Controllers */

RM.controller('HeaderController', [ '$scope', '$location',
		function($scope, $location) {
			$scope.isActive = function(viewLocation) {
				var re = new RegExp(viewLocation);
				return re.test($location.path());
			};
		} ]);

RM.controller('MemberLoginController', [ '$scope', '$location', '$rootScope',
		'$cookieStore', 'Member', 'toaster',
		function($scope, $location, $rootScope, $cookieStore, Member, toaster) {
			$scope.success = true;
			$scope.member = {};
			$scope.login = function() {

				Member.login($scope.member, function(data) {
					// check the response if signed in
					if (data.success) {
						toaster.pop('success', "You are signed in!");
						$rootScope.member = data.member;
						// create the cookie 'token'
						$cookieStore.put("token", data.token);
						// redirect to memberProfile
						$location.path('/memberProfile');
					} else {

						toaster.pop('error', "Invalid login!");
					}
				});
			};
		} ]);

RM.controller('SignupController', [ '$scope', 'Member', 'toaster',
		function($scope, Member, toaster) {
			$scope.success = true;
			$scope.memberProfile = {};
			$scope.saveNewMember = function() {
				Member.save($scope.member, function(data) {
					$scope.message = data.message;
					$scope.success = data.success;
					if (data.success) {
						toaster.pop('success', "Now sign in :)");
					} else {
						toaster.pop('error', data.message);
					}
				});
			};
		} ]);

RM.controller('MemberProfileController', [ '$scope', '$rootScope',
		'MemberProfile', function($scope, $rootScope, MemberProfile) {
			$scope.memberProfile = {};
			// wait for the event 'memberLoaded'
			$scope.$on('memberLoaded', function() {
				$scope.memberProfile = MemberProfile.findByMemberId({
					id : $rootScope.member.id
				});
			});
			$scope.saveNewMemberProfile = function() {
				$scope.memberProfile.member = $rootScope.member;
				MemberProfile.save($scope.memberProfile, function(){
					$scope.memberForm.$setPristine();
				});
			};
		} ]);
RM.controller('ListingController',['$scope', '$rootScope', '$upload', '$modal', 'toaster', 'Listing', 'Flat',
        function($scope, $rootScope, $upload, $modal, toaster, Listing){
			$scope.listing = {};
			
			// find all flats that belong to the signed in member
			$scope.$on('memberLoaded', function() {
				$scope.listings = Listing.findAllByMemberId({
					id : $rootScope.member.id
				});
			});
			
			$scope.saveNewListing = function(){
				$scope.listing.member = $rootScope.member;
				$scope.listing.date = Date.now();
				Listing.save($scope.listing, function(data){
					$scope.listing = data;
					$scope.listings = Listing.findAllByMemberId({
						id : $rootScope.member.id
					});
					$scope.listing = {};
					$scope.addListingForm.$setPristine();
				});
			};
			$scope.getListing = function(listingId) {
				$scope.listing = Listing.get({
					id : listingId
				});

			};
}]);
























RM.controller('FlatController', [
		'$scope',
		'$rootScope',
		'$upload',
		'$modal',
		'Flat',
		'FlatImage',
		function($scope, $rootScope, $upload, $modal, Flat, FlatImage) {
			$scope.flat = {};
			// find all flats that belong to the signed in member
			$scope.$on('memberLoaded', function() {
				$scope.flats = Flat.findAllByMemberId({
					id : $rootScope.member.id
				});
			});
			$scope.saveNewFlat = function() {
				// save current member
				$scope.flat.member = $rootScope.member;
				Flat.save($scope.flat, function(data) {
					$scope.flat = data;
					$scope.flats = Flat.findAllByMemberId({
						id : $rootScope.member.id
					});
					$scope.flat = {};
					$scope.addFlatForm.$setPristine();
				});
			};
			// find the selected flat for edit
			$scope.getFlat = function(flatId) {
				$scope.flat = Flat.get({
					id : flatId
				});

			};
			// find the selected flat to delete
			$scope.deleteFlat = function(flatId) {
				Flat.remove({
					id : flatId
				}, function() {
					$scope.flats = Flat.findAllByMemberId({
						id : $rootScope.member.id
					});
				});
			};

			// creates modal window Upload images
			$scope.modalCreate = $modal({
				scope : $scope,
				template : 'templates/modal-form-notitle.tpl.html',
				contentTemplate : 'forms/imageUpload.html',
				show : false
			});

			// show modal window
			$scope.showUpload = function(flatId) {
				$scope.flatId = flatId;
				// get images for the flat
				$scope.images = FlatImage.getImagesByFlatId({
					id : flatId
				}, function(data) {
					$scope.modalCreate.show();
				});

			};

			$scope.basePath = RMUtil.basePath;

			// upload flatImage
			$scope.onFileSelect = function($files) {
				function onSuccess(data, status, headers, config) {
					$scope.flatImage = data;
					$scope.images = FlatImage.getImagesByFlatId({
						id : $scope.flatId
					});
				}
				function onError(data, status, headers, config) {
					console.log("error");
				}
				for (var i = 0; i < $files.length; i++) {
					var file = $files[i];
					$scope.upload = $upload.upload(
							{
								url : RMUtil
										.ctx("/data/rest/flatImages/upload/"
												+ $scope.flatId),
								data : {
									id : $scope.flatId
								},
								file : file
							}).success(onSuccess).error(onError);
				}
			};
		} ]);
