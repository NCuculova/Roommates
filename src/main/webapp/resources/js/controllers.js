'use strict';

/* Controllers */

RM.controller('HeaderController', [ '$scope', '$location',
		function($scope, $location) {
			$scope.isActive = function(viewLocation) {
				var re = new RegExp(viewLocation);
				return re.test($location.path());
			};
		} ]);

RM.controller('MembersController', [ '$scope', 'Member',
		function($scope, Member) {
			$scope.member = {};
			$scope.saveNewMember = function() {
				Member.save($scope.member);
			};
		} ]);