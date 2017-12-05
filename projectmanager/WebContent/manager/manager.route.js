angular.module("myApp").config(function($routeProvider) {
	$routeProvider.when("/manager", {
		templateUrl : "manager/managerMain.html",
		controller : "managerCtrl"
	});
});