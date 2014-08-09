'use strict';

/* Controllers */

RM.controller('HeaderController', [ '$scope', '$location',
		function($scope, $location) {
			$scope.isActive = function(viewLocation) {
				var re = new RegExp(viewLocation);
				return re.test($location.path());
			};
		} ]);

RM.controller('MemberLoginController', [ '$scope', 'Member', 'toaster',
		function($scope, Member, toaster) {
			$scope.member = {};
			$scope.login = function() {
				Member.login($scope.member, function(data){
					console.log(data);
					if(data.success){
												
					}
					else{
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
					if(data.success) {
						toaster.pop('success', "Now sign in :)");						
					} else {
						toaster.pop('error', data.message);
					}
				});
			};
		} ]);