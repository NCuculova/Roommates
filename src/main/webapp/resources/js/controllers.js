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

RM.controller('FlatController', [ '$scope','$rootScope', 'Flat', 'toaster',
		function($scope, $rootScope, Flat, toaster) {
			$scope.flat = {};
			$scope.saveNewFlat = function() {
				if($scope.flat.area == null || $scope.flat.heating == null)
				{
					toaster.pop('warning', "You have empty fields!");
				}
				$scope.flat.member = $rootScope.member;
				Flat.save($scope.flat);
				toaster.pop('success', "The flat has been added");
				$scope.flat = {};
			};
		} ]);

RM.controller('MyFlatController', [ '$scope', '$rootScope', 'Flat', 'toaster',
        function($scope, $rootScope, Flat, toaster) {
		
		$scope.flats = Flat.query();
		
} ]);
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