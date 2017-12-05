angular.module("myApp").controller("empReport", function($scope, $http) {
	var user=localStorage.getItem(userid)
	
	
	$http.get("/projectmanager/rest/hourReport/getLastSevenDays?userid="+user)
	.then(function(response) {
		$scope.emploReport = response.data;
		console.log(response.data);
		
	});

});