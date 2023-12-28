package in.tejaTech.service;

import java.util.List;
import java.util.Map;

import in.tejaTech.entity.Plan;

public interface PlanService {

	//for drop-down data we are using key-value pair bcoz we are displaying the data
	public Map<Integer, String> getPlanCategories();
	
	// status of the record whether it is inserted or not
	public boolean savePlan(Plan plan);
	
	//Display all plans for the we are taking List<>
	public List<Plan> getAllPlans();
	
	//for editable mode 
	public Plan getPlanById(Integer planId);
	
	// update
	public boolean updatePlan(Plan plan);
	
	//for deleting plan (hard-delete)
	public boolean deletePlanById(Integer planId);
	
	// for active or inactive a plan (switch button for soft-delete)
	public boolean planStatusChange(Integer planId,String status);
}
