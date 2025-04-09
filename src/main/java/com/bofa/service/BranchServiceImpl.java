package com.bofa.service;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bofa.model.Branch;
import com.bofa.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {
	private BranchRepository branchRepository;
	
	public BranchServiceImpl(BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
	}

	@Override
	public Branch addBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public Branch getBranch(Long branchId) {
		Optional<Branch> branch_id = branchRepository.findById(branchId);
		if (branch_id.isPresent()) {
			return branch_id.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found");
		}
	}

	@Override
	public List<Branch> getAllBranches() {
		List<Branch> branches = branchRepository.findAll();
		if (branches.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Branches found");
		}
		branches.forEach(branch -> {
			System.out.println(branch.getBranchName());
		});
		
		return branches;
	}

	@Override
	public void deleteBranch(Long branchId) {
		if (branchRepository.findById(branchId).isPresent()) {
			branchRepository.deleteById(branchId);
			System.out.println("Branch is deleted.");
		}
		else {
			System.out.println("No Branch exists with given branch ID");
		}
		
	}

	@Override
	public Branch updateBranch(Branch branch) {
		if (branch.getBranchName() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Branch not found or Invalid Name");
		}
		switch (branch.getBranchName().toLowerCase()) {
		case "fowleravenue":
			System.out.println();
		case "fletcherstr":
			System.out.println();
		}
		return branchRepository.save(branch);
	}
	
}