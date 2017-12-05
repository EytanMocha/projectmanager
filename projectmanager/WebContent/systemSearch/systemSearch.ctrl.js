angular.module("myApp").controller(
		"systemSearchCtrl",
		function($scope, $http, $location) {
			var s;
			for (var y = 2015; y <= 2017; y++) {
				s = y;
				
				$("#YearTime").append("<option value="+s+">"+s+"</option>");
			}
			var k;
			for (var x = 1; x <= 12; x++) {
				k = x;
				
				$("#MonthTime").append("<option value="+k+">"+k+"</option>");
				
				
			}
			
			$http.get("/projectmanager/rest/employee/getallemployees").then(
					function(response) {
						$scope.emplo = response.data;
					})
			$http.get("/projectmanager/rest/customer/getallcustomer").then(
					function(response) {

						$scope.custom = response.data;
					})
			$http.get("/projectmanager/rest/project/getAllProjects").then(
					function(response) {
						$scope.allpro = response.data;
					})
			$http.get("/projectmanager/rest/hourReport/getAllAllHours")
					.then(function(response) {
						$scope.Reporting = response.data;
						console.log($scope.Reporting);
					});
			
		  
			//parameters that comes from the server that holding numbers that represents Objects 
			$scope.searchData = function() {

				if ($scope.emp == undefined) {
					$scope.emp = 0;
				}

				if ($scope.pro == undefined) {
					$scope.pro = 0;
				}

				if ($scope.cus == undefined) {
					$scope.cus = 0;
				}
				console.log($scope.emp)
				console.log($scope.pro)
				console.log($scope.cus)
				
			

				$http.get(
						"/projectmanager/rest/hourReport/getByYearMonthManager?"
								+ "year=" + $("#YearTime").val() + "&month="
								+  $("#MonthTime").val() + "&employeeid=" + $scope.emp
								+ "&projectid=" + $scope.pro + "&customerid="
								+ $scope.cus).then(function(response) {
					$scope.Reporting = response.data;
					console.log($scope.Reporting);

				})
			}
			 
		});