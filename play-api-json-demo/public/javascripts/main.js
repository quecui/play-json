var app = angular.module('PlayJson', ['services']);

app.config(function ($httpProvider) {
    $httpProvider.defaults.headers.get = {'Content-Type': 'application/json'};
    $httpProvider.defaults.headers.post = {'Content-Type': 'application/json'};
})

app.controller('ctrlDemo', function ($scope, playService) {
    $scope.name = "";
    $scope.username = "";
    $scope.age = "";
    $scope.nameRespone = "";

    $scope.getData = function () {
        playService.testData().then(function (response) {
            $scope.username = response.data.name;
            $scope.age = response.data.age;
        })
    }

    $scope.postData = function () {
        if ($scope.name == "" || $scope.name == undefined) {
            alert("Input your name, please")
        } else {
            var dataJson = {"name": $scope.name, "age": 22};
            playService.postData(dataJson).then(function (response) {
                $scope.nameRespone = response.data;
            })
        }
    }
})


