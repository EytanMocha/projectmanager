var userid;
angular.module("myApp").config(function($routeProvider) {

	$routeProvider.when("/Costumer", {
		templateUrl : "customerReport/customerReport.html",
		controller : "customerReportCtrl"
	});
	$routeProvider.when("/CostumerHours", {
		templateUrl : "customerReport/coustomerHours.html",
		controller : "customerReportCtrl"
	});
});