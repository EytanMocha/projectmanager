angular.module("myApp").config(function($routeProvider) {
	$routeProvider.when("/ActiveProject", {
			templateUrl : "activeP/ActiveProject.html",
			controller : "ActiveProjectCtrl"
		});
});