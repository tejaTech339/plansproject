package in.tejaTech.restcont;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.tejaTech.constants.AppConstants;
import in.tejaTech.entity.Plan;
import in.tejaTech.properties.AppProperties;
import in.tejaTech.service.PlanService;

@RestController
public class PlanRestController {
/*
	@Autowired
	private PlanService planService;

	@Autowired
	private AppProperties appProperties;
*/	
	
	private PlanService planService;
	
	//private AppProperties appProperties;
	
	private Map<String, String> messages;
	
	//Constructor Injection
	public PlanRestController(PlanService planService,AppProperties appProperties) {
		
		this.planService=planService;
		//this.appProperties=appProperties;
		this.messages=appProperties.getMessages();
		//System.out.println(messages);
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> planCategories = planService.getPlanCategories();

		return new ResponseEntity<>(planCategories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {

		//String responseMsg = "";
		String responseMsg = AppConstants.EMPTY_STR;

		//Map<String, String> messages = appProperties.getMessages();

		boolean isSaved = planService.savePlan(plan);
		if (isSaved) {
			// String string = messages.get("planSaveSucc");
			responseMsg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> allPlans() {

		List<Plan> allPlans = planService.getAllPlans();

		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/editPlan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {

		Plan plan = planService.getPlanById(planId);

		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@PutMapping("/updatePlan")
	public ResponseEntity<String> updatePlan(Plan plan) {

		String responseMsg = AppConstants.EMPTY_STR;
		//Map<String, String> messages = appProperties.getMessages();

		boolean isUpdated = planService.updatePlan(plan);
		if (isUpdated) {
			responseMsg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

	@DeleteMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {

		String responseMsg = AppConstants.EMPTY_STR;
		
		//Map<String, String> messages = appProperties.getMessages();

		boolean isDeleted = planService.deletePlanById(planId);

		if (isDeleted) {
			responseMsg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

	@PutMapping("/status-changed/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {

		String responseMsg = AppConstants.EMPTY_STR;
		
		//Map<String, String> messages = appProperties.getMessages();

		boolean isStatusChanged = planService.planStatusChange(planId, status);
		if (isStatusChanged) {
			responseMsg = messages.get(AppConstants.PLAN_STATUS_CHANGE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}
}
