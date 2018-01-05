package com.pro2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="JOB_M")
@Component
public class Job {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String jobTittle;
private String jobDescription;
private String skillRequried;
private String location;
private String yearsofExperience;
private String salary;
private String companyName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getJobTittle() {
	return jobTittle;
}
public void setJobTittle(String jobTittle) {
	this.jobTittle = jobTittle;
}
public String getJobDescription() {
	return jobDescription;
}
public void setJobDescription(String jobDescription) {
	this.jobDescription = jobDescription;
}
public String getSkillRequried() {
	return skillRequried;
}
public void setSkillRequried(String skillRequried) {
	this.skillRequried = skillRequried;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getYearsofExperience() {
	return yearsofExperience;
}
public void setYearsofExperience(String yearsofExperience) {
	this.yearsofExperience = yearsofExperience;
}
public String getSalary() {
	return salary;
}
public void setSalary(String salary) {
	this.salary = salary;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
}
