angular.module("myApp").controller("systemmanagementCtrl", function($scope, $http) {

	var s;
	for (var i = 0; i < 24; i++) {
		s = i +":00";
		
		$("#StartTime").append("<option value="+s+">"+s+"</option>");
		$("#EndTime").append("<option value="+s+">"+s+"</option>");
		
	}
	
		
		$scope.SendDays = function (){
			 var sunday = $("#Sunday").is(":checked") ? "true" : "false";
			 var monday = $("#Monday").is(":checked") ? "true" : "false";
			 var tuesday = $("#Tuesday").is(":checked") ? "true" : "false";
			 var wednesday = $("#Wednesday").is(":checked") ? "true" : "false";
			 var thursday = $("#Thursday").is(":checked") ? "true" : "false";
			 var friday = $("#Friday").is(":checked") ? "true" : "false";
			 var saturday = $("#Saturday").is(":checked") ? "true" : "false";
				$http.get("/projectmanager/rest/Properties/setdaysofWork?sunday="
						+sunday+
						"&monday="+monday+
						"&tuesday="+tuesday+
						"&wednesday="+wednesday+
						"&thursday="+thursday+
						"&friday="+friday+
						"&saturday="+saturday)
					.then(function(response) {
				    $scope.days = response.data;
				    if( $scope.days == 'OK'){
				    	alert("You set Days to report !!");
				    }
				});
}
		$scope.setTime = function(){
			$http.get("/projectmanager/rest/Properties/setHourList?startHour="
					+$("#StartTime").val()+"&endHour="+$("#EndTime").val())
					.then(function(response){
						$scope.reply = response.data;
						console.log($scope.reply);
			});
	

}
		
	

	})