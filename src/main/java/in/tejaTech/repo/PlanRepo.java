package in.tejaTech.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.tejaTech.entity.Plan;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
