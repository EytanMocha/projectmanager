var userid;
angular.module("myApp").controller("Login",function($scope, $http , $location) {
		$(".customer").hide();
		 $(".employee").hide();
		 $(".manager").hide();
		 $(".header").hide();
		 $(".footer").hide();
		 $(".dev").hide();
	
		 
		 
		 $scope.forgetpassword = function(){	
		    	$("#passworR").dialog();
		    
		    	$scope.userRequst = function(){
		    		
			    	$http.get("/projectmanager/rest/users/sendmail?user="
			    			+$scope.forgotUser)
			    			.then(function(response){
			    				$scope.sendmail = response.data;
			    				console.log($scope.sendmail);
			    				
			    				if($scope.sendmail.id == 0){
			    					alert("check your email... we sent mail"
							    			 +" with your password and your username! ");
							    	console.log("forgetpassword");
			    				}else if($scope.sendmail == null){
			    					alert("no such user name ");
			    				}
			    				
	    				$("#forgotUser").val("");
	    				$("#passworR").dialog( "destroy" );
		    				
	    			});
		    		
		    	};
		    };
		
		$scope.getuser = function() {
			
			$http.get("/projectmanager/rest/users/username?username="+$scope.username+"&password="+$scope.password)
			.then(function(response) {
					var res = response.data;
					
			var	userid_ = res.id
			localStorage.setItem(userid, userid_);
				console.log(userid)
				
				
		
				
				if (res.type == "customer"){
					userid_ = res.id
					console.log(userid)

						
						$location.path("./customerdata.html");
						$(".header").show();
						 $(".footer").show();
						 $(".customer").show();
						 $(".footer").show();
						 $(".dev").show();
						 
					}else if(res.type == "emp"){
						userid_ = res.id
						console.log(userid)

						
						
						$location.path("/projectdata.html");
						 $(".header").show();
						 $(".footer").show();
						 $(".dev").show();
						 $(".employee").show();
						 
						
					}else if(res.type =="manager"){
					
						
						alert(res.type);
						console.log(userid)
						$location.path('/manager');
						$(".header").show();
						 $(".footer").show();
						 $(".manager").show();
						 $(".footer").show();
						 $(".dev").show();
						

					
					}else{
						alert("wrong username or password");
					}
			});	
 			
		};			
	});
	

