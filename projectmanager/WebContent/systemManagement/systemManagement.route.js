angular.module("myApp").config(function($routeProvider) {
	$routeProvider.when("/management", {
			templateUrl : "systemManagement/systemManagement.html",
			controller : "systemmanagementCtrl"
		});
	});