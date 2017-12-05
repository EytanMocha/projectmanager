angular.module("myApp").config(function($routeProvider) {
	$routeProvider.when("/Settings", {
			templateUrl : "customerHours/customerHours.html",
			controller : "CustCtrl"
		});
	});