package in.tejaTech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tejaTech.entity.Plan;
import in.tejaTech.entity.PlanCategory;
import in.tejaTech.repo.PlanCategoryRepo;
import in.tejaTech.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private PlanCategoryRepo planCategoryRepo;
	
	@Override
	public Map<Integer, String> getPlanCategories() {

		List<PlanCategory> categories = planCategoryRepo.findAll();
		
		Map<Integer, String> categoryMap=new HashMap<>();
		
		//1.Streams:
		categories.forEach(category->{
			
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		
		//2.foreach-loop:
	/*	for(PlanCategory cat:categories) {
			
			categoryMap.put(cat.getCategoryId(), cat.getCategoryName());
		} */
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {

		Plan saved = planRepo.save(plan);
		
	/*	if(saved.getPlanId()!=null) {
			return true;
		}
		return false; */
		
	/*	return saved.getPlanId()!=null?true:false; */
		
		return saved.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlans() {

	/*	List<Plan> findAll = planRepo.findAll();
		
		return findAll;*/
		
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {

		Optional<Plan> findById = planRepo.findById(planId);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		
		Plan save = planRepo.save(plan);//upsert method
		
	/*	if(plan.getPlanId()!=null) {
			return true;
		}
		return false;*/
		
		return plan.getPlanId()!=null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {

		boolean status=false;
		
		try {
			   planRepo.deleteById(planId);
			   status=true;
		} catch (Exception e) {
			e.printStackTrace();
			status=false;
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		
	/* we need to properly do the updation otherwise 
	 * other columns will get null values(default values).
	 * 
	 * ex:  Plan plan=new Plan();
	 *      plan.setPlanId(planId);
	 *      plan.setActiveSW(status);
	 *      planRepo.save(plan);
	 */
		// Approach
		Optional<Plan> findById = planRepo.findById(planId);
		
		if(findById.isPresent()) {
			
			Plan plan=findById.get();
			plan.setActiveSW(status);
			
			planRepo.save(plan);
			
			return true;
		}
		return false;
	}

}
