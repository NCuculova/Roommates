'use strict';

/* Services */

angular.module('RM.services', [ 'chieffancypants.loadingBar' ]).value(
		'version', '0.1');

RM.factory('Member', [ '$resource', function($resource) {
	return $resource(RMUtil.ctx('/data/rest/members/:id'), {}, {
		'login' : {
			method : 'POST',
			url : RMUtil.ctx('/data/rest/members/login')
		},
		'auth' : {
			method : 'POST',
			url : RMUtil.ctx('/data/rest/members/auth')
		}
	});
} ]);

RM.factory('MemberProfile', [ '$resource', function($resource) {
	return $resource(RMUtil.ctx('/data/rest/memberProfile/:id'), {},{
		'findByMemberId':{
			method : 'GET',
			url : RMUtil.ctx('/data/rest/memberProfile/member/:id')
		}
	});
} ]);

RM.factory('Flat', [ '$resource', function($resource) {
	return $resource(RMUtil.ctx('/data/rest/flats/:id'), {},{
		'findAllByMemberId':{
			method : 'GET',
			isArray: true,
			url : RMUtil.ctx('/data/rest/flats/member/:id')
		}
	});
} ]);

RM.factory('Listing', [ '$resource', function($resource) {
	return $resource(RMUtil.ctx('/data/rest/listings/:id'), {},{
		'findAllByMemberId':{
			method : 'GET',
			isArray: true,
			url : RMUtil.ctx('/data/rest/listings/member/:id')
		}
	});
} ]);

RM.factory('ListLook', [ '$resource', function($resource) {
	return $resource(RMUtil.ctx('/data/rest/listingslook/:id'), {},{
		
	});
} ]);

RM.factory('FlatImage', [ '$resource', function($resource) {
	return $resource(RMUtil.ctx('/data/rest/flatImages/:id'), {},{
		'getImagesByFlatId':{
			method : 'GET',
			isArray: true,
			url : RMUtil.ctx('/data/rest/flatImages/image/:id')
		}
	});
} ]);