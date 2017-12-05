angular.module("myApp").controller("managerCtrl", function($scope, $http) {
	

	
	
		$http.get("/projectmanager/rest/project/getAllProjects")
				.then(function(response) {
					$scope.allproject = response.data;
					console.log(response.data);
		
		$http.get("/projectmanager/rest/customer/getallcustomer")
				.then(function(response) {
					$scope.activecustomer = response.data;
					console.log(response.data);
		
	
		$http.get("/projectmanager/rest/project/getNearEndProject")
		.then(function(response) {
			$scope.projectsends = response.data;
				console.log(response.data);
		
		
		});
				});	
				
	});
		});