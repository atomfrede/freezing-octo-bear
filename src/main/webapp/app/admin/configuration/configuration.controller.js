'use strict';

angular.module('matetrackerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('configuration', {
                parent: 'admin',
                url: '/configuration',
                data: {
                    roles: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/admin/configuration/configuration.html',
                        controller: 'ConfigurationController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('configuration');
                        return $translate.refresh();
                    }]
                }
            });
    })
    .controller('ConfigurationController', function ($scope, ConfigurationService) {
        $scope.configuration = ConfigurationService.get();
    });
