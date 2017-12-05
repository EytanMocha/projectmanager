angular.module("myApp").config(function($routeProvider) {
		$routeProvider.when("/", {
			templateUrl : "login/loginPage.html",
			controller: "Login"
		});
});

