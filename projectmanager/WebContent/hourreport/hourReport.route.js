angular.module("myApp").config(function($routeProvider) {
	$routeProvider.when("/Reportinghour", {
		templateUrl : "hourreport/Reportinghour.html",
		controller : "ReportinghourCtrl"
	})
});