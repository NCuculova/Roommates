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
			$scope.member = {};// se kreira prazen objekt
			$scope.saveNewMember = function() {
				$scope.member = {};// se prazni formata, na call-back funkcija na signin 
				Member.save($scope.member);// save vo baza
				toaster.pop('success', "You are signed in");
			};
		} ]);