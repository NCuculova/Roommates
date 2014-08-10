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
		'MemberProfile', 'toaster',
		function($scope, $rootScope, MemberProfile, toaster) {
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

RM.controller('FlatController', [ '$scope', 'Flat', 'toaster',
       function($scope, Flat, toaster) {
       		$scope.success = true;
            $scope.flat = {};
            //treba da se stavi logikata za member tuka !!! za da se zeme od logiraniot i da se stavi vo baza !!!
            $scope.saveNewFlat = function() {
            	Flat.save($scope.flat, function(data) {
                	$scope.message = data.message;
                	$scope.success = data.success;
                    	if(data.success) {
                    		toaster.pop('success', "New flat added!");						
                        } else {
                        	toaster.pop('error', data.message);
                        }
                	});
               	};
         	} ]);

