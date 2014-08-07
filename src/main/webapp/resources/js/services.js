'use strict';

/* Services */

angular.module('RM.services', [ 'chieffancypants.loadingBar' ]).value(
		'version', '0.1');

RM.factory('Member', [ '$resource', function($resource) {
	return $resource(WPUtil.ctx('/data/rest/members/:id'), {});
} ]);
