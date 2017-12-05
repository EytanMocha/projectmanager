angular.module("myApp").controller("employeedataCtrl", function($scope, $http,$location) {
	localStorage.getItem(userid)
	console.log(userid)

		
		$http.get("/projectmanager/rest/project/getAllProjects")
		.then(function(response) {
			$scope.project = response.data;	
			
		});
		
		
		$http.get("/projectmanager/rest/employee/getallemployees").then(
				function(response) {
					$scope.employee = response.data;
					console.log(response.data);
					
					
					    $scope.index == -1;
				        $scope.tempForm = [];
				        
						$scope.Savedata = function(index)
				        {
				            if(index ==  $scope.index)
				            {
				            	console.log("starting create");
				            	$http.get("/projectmanager/rest/employee/create?firstname="+$scope.tempForm.firstname+
				            			"&lastname="+$scope.tempForm.lastname+
				            			"&email="+$scope.tempForm.email+"&phone="+$scope.tempForm.phone+"&user="+
				            			$scope.tempForm.username+"&password="+$scope.tempForm.passsword )
				            			.then(function(response) {
										;
										console.log(response.data)
										});
				            	//add
				                $scope.employee.push($scope.tempForm);
				                $scope.tempForm=[];
				                $scope.index = -1; 
				               
				                 }
				            else
				            {//edit
				            	console.log("starting update");
				            	console.log($scope.employee[index].id);
				            	$http.get("/projectmanager/rest/employee/update?id="+
				            			$scope.tempForm.id+"&firstname="+				            			
				            			$scope.tempForm.firstname+"&lastname="+$scope.tempForm.lastname+
				            			"&email="+$scope.tempForm.email+"&phone="+$scope.tempForm.phone)
				            			.then(function(response) {
										
										console.log(response.data)
										});
				            	
				                $scope.employee[index] = $scope.tempForm;
				                $scope.tempForm=[];
				                $scope.index = -1;
				            }
				        }
						$scope.RemoveRow = function(index){
							if(confirm("do you really want to delete? ")==true){
							$http.get("/projectmanager/rest/employee/deleteEmployee?id="+$scope.employee[index].id)
		            			.then(function(response) {
		            				$scope.employee.splice(index,1);
									
								});
						} };
					            
					            $scope.editRow = function(index)
					            {	               
					            	$scope.index = index;
					            	$scope.tempForm.id =  $scope.employee[index].id;
					                $scope.tempForm.firstname = $scope.employee[index].firstname,
					                $scope.tempForm.lastname = $scope.employee[index].lastname,
					                $scope.tempForm.email = $scope.employee[index].email;
					                $scope.tempForm.phone = $scope.employee[index].phone;
					                $scope.tempForm.username = $scope.employee[index].username;
					                $scope.tempForm.password = $scope.employee[index].password;
					            };
				});
		$http.get("/projectmanager/rest/employeeProject/getallemployeesConnect").then(
				function(response) {
					$scope.emptopro = response.data;
					console.log($scope.emptopro);
		
		$scope.Zzzz = function(index){
			
			$http.get("/projectmanager/rest/employeeProject/createEmployeeProject?employee="+$scope.pro+
					"&project="+$scope.emp)
    			.then(function(response) {
    				console.log(response.data)
    				if (response.data == null) {
    					console.log("wrong")
						
					}
					
				});
		 };
	
		
	});
});