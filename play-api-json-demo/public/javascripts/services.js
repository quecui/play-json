var services = angular.module('services', []).factory('playService', playService);

function playService($http) {
    return {
        testData: testData,
        postData: postData
    }

    function testData() {
        return $http({
            url: 'http://localhost:9000/users',
            method: 'GET'
        })
    }

    function postData(data) {
        return $http({
            url: 'http://localhost:9000/postUser',
            method: 'POST',
            data: data
        })
    }
}