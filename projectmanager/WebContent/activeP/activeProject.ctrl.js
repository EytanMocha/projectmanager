angular.module("myApp").controller("ActiveProjectCtrl", function($scope, $http) {
		$http.get("/projectmanager/rest/project/getProjectsByStatus").then(
				function(response) {
					$scope.activeproject = response.data;
					console.log(response.data);
				});

	});