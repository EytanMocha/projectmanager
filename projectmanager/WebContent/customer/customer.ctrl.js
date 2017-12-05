angular.module("myApp").controller("customerdatatCtrl", function($scope, $http) {
	localStorage.getItem(userid)
	console.log(userid)

		$http.get("/projectmanager/rest/customer/getallcustomer").then(
				function(response) {
					$scope.customer = response.data;
					console.log(response.data);
					
					
				       
				        $scope.index == -1;
				        $scope.tempForm = [];
				
						
						$scope.Savedata = function(index)
				        {
							
				            if(index ==  $scope.index)
				            {
				            	console.log("starting create");
				            	$http.get("/projectmanager/rest/customer/create?companyname="+$scope.tempForm.companyname+"&companynumber="+$scope.tempForm.companynumber+
				            			"&contactname="+$scope.tempForm.contact+"&email="+$scope.tempForm.email+"&phone="+$scope.tempForm.phone+"&username="+
				            			$scope.tempForm.username+"&password="+$scope.tempForm.password)
				            			.then(function(response) {
										
										console.log(response.data)
										});
				            	
				            	
				            	//add
				                $scope.customer.push($scope.tempForm);
				                $scope.tempForm=[];
				                $scope.index = -1; 
				                
				               
				                 }
				            else
				            {//edit
				            	console.log($scope.tempForm.id);
				            	console.log("starting update");
				            	$http.get("/projectmanager/rest/customer/update?id="
				            			+$scope.tempForm.id+
				            			"&companyname="+$scope.tempForm.companyname+"&companynumber="+$scope.tempForm.companynumber
				            			+"&contact="+$scope.tempForm.contact+"&email="+$scope.tempForm.email+"&phone="+$scope.tempForm.phone)
				            			.then(function(response) {
										console.log(response.data)
										});
				                $scope.customer[index] = $scope.tempForm ;
				                $scope.tempForm=[];
				                $scope.index = -1;
				            }
				            
				        }
						$scope.RemoveRow = function(index){
							if(confirm("do you really want to delete?")==true){
							$http.get("/projectmanager/rest/customer/deleteCustomer?id="+$scope.customer[index].id)
			            			.then(function(response) {
									
									console.log(response.data)
									});
					        $scope.customer.splice(index,1);
						} };
					            
					            $scope.editRow = function(index)
					            {	               
					            	$scope.index=index;
					            	$scope.tempForm.id =  $scope.customer[index].id;
					                $scope.tempForm.companyname = $scope.customer[index].companyname;
					                $scope.tempForm.companynumber = $scope.customer[index].companynumber;
					                $scope.tempForm.contact = $scope.customer[index].contact;
					                $scope.tempForm.email = $scope.customer[index].email;
					                $scope.tempForm.phone = $scope.customer[index].phone;
					                $scope.tempForm.username = $scope.customer[index].username;
					                $scope.tempForm.password = $scope.customer[index].password;
					                
					               
					            };
					            
					            
				});
		
		

	});