package com.bofa.model;

import jakarta.persistence.*;

public class Branch {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long branchId;
	public String branchName;
	public String branchAddress;
	protected String branchManager;
	public String branchCode;
	public String timings;
	
	public long getBranchId() {
		return branchId;
	}
	
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchName() {
		return branchName; 
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getBranchAddress() {
		return branchAddress;
	}
	
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	public String getManager() {
		return branchManager;
	}
	
	public void setManager(String branchManager) {
		this.branchManager = branchManager;
	}
	
	public String branchCode() {
		return branchCode;
	}
	
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getTimings() {
		return timings;
	}
	
	public void setTimings(String timings) {
		this.timings = timings;
	}
	
	
}

