
angular.module("myApp").config(function($routeProvider) {
	
	$routeProvider.when("/employeeSeven", {
		templateUrl : "employeeReport/employeeHours.html",
		controller : "empReport"
	})
});