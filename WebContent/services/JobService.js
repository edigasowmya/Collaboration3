/**
 * 
 */

app.factory('JobService',function($http){
	
	var BASE_URL = "http://localhost:8182/backend2";
	
	var jobService = {}
	
	jobService. addJobs = function(job){
		
		return $http.post(BASE_URL+"/addjob",job)
	}
	
	jobService.getAllJobs = function(){
		
		return $http.get(BASE_URL+"/getalljobs")
	}
	
	/*jobService.getJob = function(jobId){
		
		return $http.get(BASE_URL+"/getJob/"+jobId)*/
	
	
	return jobService;
})
