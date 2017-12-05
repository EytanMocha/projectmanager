angular.module("myApp").controller("customerReportCtrl", function($scope, $http) {
	
	
	
	var user=localStorage.getItem(userid)

		$http.get("/projectmanager/rest/project/getProjectsUser?userid="+user).then(
				function(response) {
					$scope.ProjectsUser = response.data;
					console.log($scope.ProjectsUser)
				});

	});