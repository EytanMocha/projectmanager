angular.module("myApp").config(function($routeProvider) {
	$routeProvider.when("/employeedata", {
		templateUrl : "employee/employeedata.html",
		controller : "employeedataCtrl"
	});
});