angular.module("myApp").config(function($routeProvider) {
	
	$routeProvider.when("/projectdata", {
		templateUrl : "project/projectdata.html",
		controller : "projectmanagementCtrl"
	});
});