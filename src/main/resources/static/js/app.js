var app = angular.module('stocksApp', []);
app.controller('StockCtrl', function($scope, $http) {
    $http.defaults.headers.post["Content-Type"] = "application/json";
    $http.get("/stocks").
        success(function (data){
            $scope.stocks = data._embedded.stocks;
        });

    $scope.checkStock = function(symbol)
    {
        $scope.stockChartUrl = "http://chart.finance.yahoo.com/z?q=b&t=6m&z=l&p=m50,m10,v&s="+symbol;
        $http.get("/api/quotes?symbol="+symbol).
            success(function (data){
                $scope.cSymbol = data.symbol;
                $scope.cPrice = data.price;
                $scope.cChange = data.change;
                $scope.c12High = data.yearHigh;
                $scope.c12Low = data.yearLow;
                $scope.cMarketCap = data.marketCap;
            });
    }

    $scope.checkStock("SPY")
});

