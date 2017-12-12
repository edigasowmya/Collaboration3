/**
 * UserService
 */
app.factory('UserService',function($http){
	
	
	var BASE_URL="http://localhost:8182/backend2";
	
	var userService={}
	
	userService.registerUser=function(user){
		console.log(user)
		return $http.post(BASE_URL + "/register",user)//4
		
	
	}
	
	userService.login=function(user){
		return $http.post(BASE_URL + "/login",user)
	}
	
	userService.logout=function(){
		return $http.get(BASE_URL + "/logout")
	}
	return userService;
})
