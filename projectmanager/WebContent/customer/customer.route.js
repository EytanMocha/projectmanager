angular.module("myApp").config(function($routeProvider) {
		$routeProvider.when("/customerdata", {
			templateUrl : "customer/customerdata.html",
			controller : "customerdatatCtrl"
		});
});