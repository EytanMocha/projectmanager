                                           /* projects Managements table*/
angular.module("myApp").controller("projectmanagementCtrl", function($scope, $http) {
		$http.get("/projectmanager/rest/project/getAllProjects").then(
				function(response) {
					$scope.allproject = response.data;
					console.log(response.data);
					console.log(userid)

					$scope.tempForm = [];
					 $scope.index = -1;
					 
				     /*add new project row   */
						$scope.Savedata = function(index)
				        {
							console.log($scope.tempForm.customer);

				            if(index == -1)
				            {
				            	
				            	
								console.log("starting create");
				            	$http.get("/projectmanager/rest/project/create?projectname="+ $scope.tempForm.projectname+"&customer="+$scope.tempForm.customer+
				            			"&customerprojectmanager="+$scope.tempForm.customerprojectmanager
				            			+"&projectmanageremail="+$scope.tempForm.projectmanageremail+"&projectmanagerephone="+$scope.tempForm.projectmanagerephone
				            			+"&startdate="+$.datepicker.formatDate('yy-mm-dd',$scope.tempForm.startdate)+"&enddate="+$.datepicker.formatDate('yy-mm-dd',$scope.tempForm.enddate))
				            			.then(function(response) {
											
											console.log( response.data)
											$http.get("/projectmanager/rest/project/getAllProjects").then(
														function(response) {console.log(response.data)})
											
										});
				             
				            	//add
				                $scope.allproject.push($scope.tempForm);
				                $scope.tempForm=[];
				                $scope.index = -1; 
				                console.log($scope.allprojectee)
				            }
				            else
				            {
				    		
				            	//edit project row 
								console.log($("#start").val())
				            	$http.get("/projectmanager/rest/project/update?id="+$scope.tempForm.id+"&projectname="+$scope.tempForm.projectname+"&customer="+$scope.tempForm.customer+
				            			"&customerprojectmanager="+$scope.tempForm.customerprojectmanager+"&projectmanageremail="+$scope.tempForm.projectmanageremail+
				            			"&projectmanagerephone="+ $scope.tempForm.projectmanagerephone+"&startdate="+$.datepicker.formatDate("yy-mm-dd",$scope.tempForm.startdate)+"&enddate="+$.datepicker.formatDate("yy-mm-dd",$scope.tempForm.enddate))
				            			.then(function(response) {
										
										console.log(response.data)
										});
				                $scope.allproject[index] = $scope.tempForm;
				                $scope.tempForm=[];
				                $scope.index = -1;
				            }
				        }
						//delete project row
						$scope.RemoveRow = function(index){
							if(confirm("are you sure you want to delete?")== true){
							$http.get("/projectmanager/rest/project/delete?id="+$scope.allproject[index].id)
	            			.then(function(response) {
							console.log(response.data)
					        $scope.allproject.splice(index,1);
	            			}
	            			)}
					            };
					            
					            //binding to form and to $scope
					            $scope.editRow = function(index)
					            {	               
					            	$scope.index = index;
					            	$scope.tempForm.id =  $scope.allproject[index].id;
					                $scope.tempForm.projectname = $scope.allproject[index].projectname,
					                $scope.tempForm.customer = $scope.allproject[index].customer,
					                $scope.tempForm.customerprojectmanager = $scope.allproject[index].customerprojectmanager;
					                $scope.tempForm.projectmanageremail = $scope.allproject[index].projectmanageremail;
					                $scope.tempForm.startdate = $scope.allproject[index].startdate,
					                $scope.tempForm.enddate = $scope.allproject[index].enddate;
					                $scope.tempForm.projectmanagerephone = $scope.allproject[index].projectmanagerephone;
					            };
							
					
				});
	
		

	});