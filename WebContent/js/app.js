
	 var app=angular.module("myApp",['ngRoute','ngCookies'])
	 
	 
	 app.config(function($routeProvider){
		 
	 	$routeProvider
	 	.when('/register',{
	 		templateUrl:'views/registrationform.html',
	 		controller:'UserController'
	 	})
	 	.when('/login',{
	 		templateUrl:'views/login.html',
	 		controller:'UserController'
	 	})
	 	.otherwise({templateUrl:'views/home.html'})
	 })
	 .when('/addjob',{
		templateUrl:'views/jobform.html',
		controller:'JobController'
	})
	.when('/getalljobs',{
		templateUrl:'views/joblist.html',
		controller:'JobController'
			
			.when("/viewBlogs",{
				templateUrl: "blogs/viewBlogs.html",
				controller: "blogController"
			})
			.when("/viewMyBlogs",{
				templateUrl: "blogs/viewUserBlogs.html",
				controller: "blogViewController"
			})
			.when("/createBlog",{
				templateUrl: "blogs/createNewBlog.html",
				controller: "blogController"
			})
			.when("/admin/manageBlog",{
				templateUrl: "blogs/manageBlog.html",
				controller: "blogController"
			})
			
	})
	 app.run(function($rootScope,$cookieStore,UserService,$location){
	 	if($rootScope.currentUser==undefined)
	 		$rootScope.currentUser=$cookieStore.get('currentUser')
	 		
	 	$rootScope.logout=function(){
	 		/*
	 		 Call middleware logout url -> Middleware - remove HttpSession attribute,update online status to false
	 		  on success - in frontend , remove cookieStore attribute currentUser, delete $rootScope.*/
	 		 
	 		UserService.logout().then(function(response){
	 			delete $rootScope.currentUser;
	 			$cookieStore.remove('currentUser')
	 			$location.path('/')
	 			
	 		},function(response){
	 			console.log(response.status)
	 			$location.path('/login')
	 		})
	 	}
 })
