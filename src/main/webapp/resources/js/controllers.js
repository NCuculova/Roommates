'use strict';

/* Controllers */

RM.controller('HeaderController', [ '$scope', '$location',
		function($scope, $location) {
			$scope.isActive = function(viewLocation) {
				var re = new RegExp(viewLocation);
				return re.test($location.path());
			};
			$scope.selection = 'login';
			$scope.$on('memberLoaded', function() {
				$scope.selection = 'logout';
			});
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
				MemberProfile.save($scope.memberProfile);
			};
		} ]);

RM.controller('FlatController', [
		'$scope',
		'$rootScope',
		'$upload',
		'Flat',
		'FlatImage',
		function($scope, $rootScope, $upload, Flat, FlatImage) {
			$scope.flat = {};
			
			
			$scope.saveNewFlat = function() {
				if($scope.flat.area == null || $scope.flat.heating == null)
				{
					toaster.pop('warning', "You have empty fields!");
				}
				$scope.flat.member = $rootScope.member;

				Flat.save($scope.flat, function(data) {
					$scope.flat = data;
				});
			};

			// upload flatImage
			$scope.onFileSelect = function($files) {
				function onSuccess(data, status, headers, config) {
					$scope.flatImage = data;
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
												+ $scope.flat.id),
								data : {
									id : $scope.flat.id
								},
								file : file
							}).success(onSuccess).error(onError);
				}

			};
		} ]);

RM.controller('MyFlatController', [ '$scope', '$rootScope', 'Flat', 'toaster', '$modal',
        function($scope, $rootScope, Flat, toaster, $modal) {
		$scope.flats = Flat.query();
		$scope.user = $rootScope.member;
		//$scope.flats = Flat.findFlatsByMember($rootScope.member); ne znam kako da go napravam go srediv so ng-show 
		


/*
WP.controller('PaperTypeController', ['$scope', 'PaperType',
                                      function($scope, PaperType) {
                                        $scope.paperType = {};
                                        $scope.types = PaperType.query();
                                        $scope.savePaperType = function() {
                                          PaperType.save($scope.paperType, function(paperType) {
                                            $scope.types = PaperType.query();
                                            $scope.paperType = {};
                                            $scope.paperTypeForm.$setPristine();
                                          });
                                        };

                                        $scope.getType = function(id) {
                                          $scope.paperType = PaperType.get({
                                            id: id
                                          });
                                        };
                                        $scope.deleteType = function(id) {
                                          PaperType.remove({
                                            id: id
                                          }, function() {
                                            $scope.types = PaperType.query();
                                            $scope.paperType = {};
                                          });

                                        };

                                      }]);*/

		
		$scope.save = function() {
			$scope.flat.member = $rootScope.member;
			Flat.save($scope.flat);
			//$scope.modalCreate.close();
		};
		
	    // creates modal 
	    $scope.modalCreate = $modal({
	        scope: $scope,
	        title: 'Edit flat data: ',
	        template: 'templates/modal-form.tpl.html',
	        contentTemplate: 'forms/editFlat.html',
	        show: false
	    });
	      
	
		// delete button
	      $scope.deleteType = function(id) {
	        Flat.remove({
	          id: id
	        }, function() {
	          $scope.flats = Flat.query();
	          toaster.pop('success', "You successfully deleted the flat");
	        });
	      };
	      
	      // edit button
	      $scope.getType = function(id) {
	    	$scope.flat = {};  
	        $scope.flat = Flat.get({
		            id: id
		          }, function() {
		            $scope.modalCreate.show();
		          });
		      };
		      
} ]);
