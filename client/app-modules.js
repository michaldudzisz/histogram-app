angular.module('Histogram', ['controller']);


angular.module('controller', [])
    .controller('codeController',
        ['$scope',
            function ($scope) {
                $scope.pole = function (data) {
                    console.log("wywolano funkcje scope.pole");
                    document.getElementById('wiadomosc').innerHTML = "Jestem angularem i dzialam";
                };
            }]);

