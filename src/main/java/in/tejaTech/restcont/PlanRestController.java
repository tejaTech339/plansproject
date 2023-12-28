package in.tejaTech.restcont;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.tejaTech.entity.Plan;
import in.tejaTech.service.PlanService;

@RestController
public class PlanRestController {

	@Autowired
	private PlanService planService;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> planCategories = planService.getPlanCategories();

		return new ResponseEntity<>(planCategories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {

		String responseMsg = "";

		boolean isSaved = planService.savePlan(plan);
		if (isSaved) {
			responseMsg = "plan saved";
		} else {
			responseMsg = "plan not saved";
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

		String msg = "";

		boolean isUpdated = planService.updatePlan(plan);
		if (isUpdated) {
			msg = "plan deleted";
		} else {
			msg = "plan deleted";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {

		String msg = "";

		boolean isDeleted = planService.deletePlanById(planId);

		if (isDeleted) {
			msg = "plan deleted";
		} else {
			msg = "plan deleted";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-changed/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status) {

		String msg = "";

		boolean isStatusChanged = planService.planStatusChange(planId, status);
		if (isStatusChanged) {
			msg = "status changed";
		} else {
			msg = "status not changed";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
