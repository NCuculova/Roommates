'use strict';

/* Controllers */

RM.controller('HeaderController', [ '$scope', '$location',
		function($scope, $location) {
			$scope.isActive = function(viewLocation) {
				var re = new RegExp(viewLocation);
				return re.test($location.path());
			};
		} ]);

RM.controller('MembersController', [ '$scope', 'Member','toaster',
		function($scope, Member,toaster) {
			$scope.member = {};
			$scope.saveNewMember = function() {
				Member.save($scope.member);
				$scope.member = {};
				toaster.pop('success', "You are signed in");
			};
		} ]);