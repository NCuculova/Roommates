'use strict';


/**
 * Controllers
 */

/**
*Controller for navigation bar
*/
RM.controller('HeaderController', [ '$scope', '$location',
		function($scope, $location) {
			$scope.isActive = function(viewLocation) {
				var re = new RegExp(viewLocation);
				return re.test($location.path());
			};
		} ]);

/**
 * Controller for memberLogin.html 
 */
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

/**
 * Controller for signup.html
 */
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

/**
 * Controller for memberProfile.html
 */
RM.controller('MemberProfileController', [ '$scope', '$rootScope',
		'MemberProfile', function($scope, $rootScope, MemberProfile) {
			$scope.memberProfile = {};
			$rootScope.getMember(function(data) {
				$scope.memberProfile = MemberProfile.findByMemberId({
					id : data.member.id
				});
			});
			$scope.saveNewMemberProfile = function() {
				$scope.memberProfile.member = $rootScope.member;
				MemberProfile.save($scope.memberProfile, function() {
					$scope.memberForm.$setPristine();
				});
			};
		} ]);

/**
 * Controller for addFlat.html
 */
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
			$rootScope.getMember(function(data) {
				$scope.flats = Flat.findAllByMemberId({
					id : data.member.id
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

/**
 * Controller for listings.html
 */
RM.controller('ListingController', [ '$scope', '$rootScope', '$modal',
		'Listing', 'Flat', function($scope, $rootScope, $modal, Listing, Flat) {

			// find all flats that belong to the signed in member
			$rootScope.getMember(function(data) {
				$scope.flats = Flat.findAllByMemberId({
					id : data.member.id
				});
				$scope.listings = Listing.findAllByMemberId({
					id : data.member.id
				});
			});

			// creates modal window for adding listings
			$scope.modalCreate = $modal({
				scope : $scope,
				template : 'templates/modal-form-notitle.tpl.html',
				contentTemplate : 'forms/flatListing.html',
				show : false
			});

			// show modal window
			$scope.showFlatListingForm = function(flat) {
				$scope.modalCreate.show();
				$scope.listing = {};
				$scope.listing.flat = flat;
			};

			$scope.saveNewListing = function() {
				$scope.listing.member = $rootScope.member;
				Listing.save($scope.listing, function(data) {
					$scope.listing = data;
					$scope.listings = Listing.findAllByMemberId({
						id : $rootScope.member.id
					});
					$scope.modalCreate.hide();
				});
			};

			// get list for edit
			$scope.editList = function(listId) {
				$scope.listing = Listing.get({
					id : listId
				}, function(data) {
					$scope.modalCreate.show();
				});
			};

			// delete selected list
			$scope.deleteList = function(listId) {
				Listing.remove({
					id : listId
				}, function() {
					$scope.listings = Listing.findAllByMemberId({
						id : $rootScope.member.id
					});
				});
			};
		} ]);

/**
 * Controller for allListings.html
 */
RM.controller('AllListingsController', [
		'$scope',
		'$rootScope',
		'$modal',
		'$location',
		'Listing',
		'FlatImage',
		'ListLook',
		function($scope, $rootScope, $modal, $location, Listing, FlatImage,
				ListLook) {

			$scope.listings = Listing.query();

			$rootScope.getMember(function(data) {
				$scope.data = data;
				// console.log(data);
			});

			// creates modal window for adding listings
			$scope.modalCreate = $modal({
				scope : $scope,
				template : 'templates/modal-form-notitle.tpl.html',
				contentTemplate : 'forms/listingProfile.html',
				show : false
			});

			// show modal window
			$scope.showListingForm = function(l) {
				$scope.basePath = RMUtil.basePath;
				$scope.listing = Listing.get({
					id : l.id
				}, function(data) {
					$scope.modalCreate.show();
					$scope.images = FlatImage.getImagesByFlatId({
						id : l.flat.id
					});
				});

			};

			//if the advertisment is expired 
			$scope.checkDate = function(dateTo) { 
				var currentDate = new Date();
				var endDate = new Date(Date.parse(dateTo.toString()));
				var dateOne = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDay()); //Year, Month, Date
			    var dateTwo = new Date(endDate.getFullYear(), endDate.getMonth(), endDate.getDay()); //Year, Month, Date
			       if (dateOne <= dateTwo) {
			            return false;
			        }else {
			            return true;
			        }
			      
			};

			// add new list look
			$scope.addNewListLook = function(l) {
				if (!$scope.data.success) {
					$location.path('/login');
					$scope.modalCreate.hide();
				} else {
					console.log("addingg");
					$scope.listLook = {};
					$scope.listLook.member = $scope.data.member;
					$scope.listLook.listing = l;
					ListLook.save($scope.listLook);
					$scope.modalCreate.hide();
				}
			};

		} ]);

/**
 * Controller for likedListings.html
 */
RM.controller('BookmarkListingController', [ '$scope', '$rootScope', '$modal',
		'Listing', 'ListLook', function($scope, $rootScope, $modal, Listing, ListLook) {

			// find all bookmarks that belong to the signed in member
			$rootScope.getMember(function(data) {
				$scope.likedListings = ListLook.getAllByMemberId({
					id : data.member.id
				});
			});

		} ]);
