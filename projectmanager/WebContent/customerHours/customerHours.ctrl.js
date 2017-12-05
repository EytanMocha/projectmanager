angular.module("myApp").controller("CustCtrl", function($scope, $http) {
	var user= localStorage.getItem(userid);
	
	$http.get("/projectmanager/rest/hourReport/EmployeeReportForCustomer?userid="+user)
	.then(function(response) {
		$scope.customerReport = response.data;
		console.log($scope.customerReport);
	});

});