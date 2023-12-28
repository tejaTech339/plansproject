package in.tejaTech.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="PLAN_CATEGORY")
public class PlanCategory {

	@Id
	@GeneratedValue
	@Column(name="CAT_ID")
	private Integer categoryId;
	@Column(name="CAT_NAME")
	private String categoryName;
	
	@Column(name="ACTIVE_SW")
	private String activeSW;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE" , insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
}
