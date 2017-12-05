angular.module("myApp").controller("ReportinghourCtrl", function($scope, $http) {
	var user=localStorage.getItem(userid)
	
	console.log(user);
	$http.get("/projectmanager/rest/employeeProject/getListOfProjects?id="+user).then(
			function(response) {
				$scope.list = response.data;
				console.log($scope.list)
			})
		$http.get("/projectmanager/rest/hourReport/getAllRport?userid="+user).then(
				function(response) {
					$scope.Reporting = response.data;
					console.log(response.data);
					
					
					 $scope.index = -1;
				        $scope.tempForm = [];
						$scope.Savedata = function(index)
				        {
				            if(index==-1)
				            {
				            
				            
				            var eit=$("#datepic").datepicker( 'getDate' );
				          $scope.date = $.datepicker.formatDate('yy-mm-dd', eit);
				            	
				            	$http.get("/projectmanager/rest/hourReport/createHourReport?employee="
				            			+user+"&project="+$scope.tempForm.project
				            			+"&date="+ $scope.date
				            			+"&starthour="+$("#startHour").val()+"&endhour="
				            			+$("#endHour").val()+"&description="+$scope.tempForm.description)
				            	
				            			.then(function(response) {
										$scope.hours = response.data;
										console.log($scope.hours)
																				
										if($scope.hours != null){
											
											$http.get("/projectmanager/rest/hourReport/getLastSevenDays?userid="+user).then(
													function(response) {
														$scope.Reporting = response.data;
													
											});			
										
										}
										
										});
				             
				            	//add
				                $scope.Reporting.push($scope.tempForm);
				                $scope.tempForm=[];
				                $scope.index = -1; 
				            }
				            else
				            {//edit
				                $scope.Reporting[index] = $scope.tempForm;
				                $scope.tempForm=[];
				                $scope.index = -1;
				            }
				        }
						$scope.RemoveRow = function(id){
					        $scope.Reporting.splice(id,1);
					            };
					            
					            $scope.editRow = function(index)
					            {	               
					            	$scope.index = index;
					                $scope.tempForm.employee = $scope.Reporting[index]['employee'],
					                $scope.tempForm.project = $scope.Reporting[index]['project'],
					                $scope.tempForm.startDate = $scope.Reporting[index]['startDate'],
					                $scope.tempForm.startHour = $scope.Reporting[index]['startHour'];
					                $scope.tempForm.endHour = $scope.Reporting[index]['endHour'];
					                $scope.tempForm.description = $scope.Reporting[index]['description'];
					            };
					
				});
		
		
		
		
		
		
		$http.get("/projectmanager/rest/Properties/getHourList")
    			.then(function(response) {
    				var report=response.data;
    				console.log(report);
    				
    				var x=report.split(",");
    				var startTime=x[0];
    				var y= startTime.split("-");
    				var startHour=parseInt(y[0]);
    				
    				
    				var endTime=x[1];
    				var z= endTime.split("-");
    				var endHour= parseInt( z[0]);
    				
    				var s;
    				for (var i = startHour; i < endHour; i++) {
    					s = i +":00";
    					
						$("#startHour").append("<option value="+s+">"+s+"</option>");
						$("#endHour").append("<option value="+s+">"+s+"</option>");
						
					}
    				
    			})
    				
		 $http.get("/projectmanager/rest/Properties/getDays")
			.then(function(response) {
		    var days = response.data;
		    var daysArr = days.split(",");
		    
		    
		    
		 $('#datepic').datepicker({
		        beforeShowDay: function(date) {
		            var day = date.getDay();
		            var result = daysArr[day].includes("true"); 
		            return [result, '', ''];
		        }
		    });
		});

	});