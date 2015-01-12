'use strict';

angular.module('matetrackerApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
