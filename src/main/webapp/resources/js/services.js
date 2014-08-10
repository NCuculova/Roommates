'use strict';

/* Services */

angular.module('RM.services', [ 'chieffancypants.loadingBar' ]).value(
		'version', '0.1');

RM.factory('Member', [ '$resource', function($resource) {
	return $resource(WPUtil.ctx('/data/rest/members/:id'), {}, {
		'login' : {
			method : 'POST',
			url : WPUtil.ctx('/data/rest/members/login')
		},
		'auth' : {
			method : 'POST',
			url : WPUtil.ctx('/data/rest/members/auth')
		}
	});
} ]);

RM.factory('MemberProfile', [ '$resource', function($resource) {
	return $resource(WPUtil.ctx('/data/rest/memberProfile/:id'), {},{
		'findByMemberId':{
			method : 'GET',
			url : WPUtil.ctx('/data/rest/memberProfile/member/:id')
		}
	});
} ]);

RM.factory('Flat', [ '$resource', function($resource) {
	return $resource(WPUtil.ctx('/data/rest/flats/:id'), {},{
		'findByMemberId':{
			method : 'GET',
			isArray: true,
			url : WPUtil.ctx('/data/rest/flats/member/:id')
		}
	});
} ]);