'use strict';

angular.module('matetrackerApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


