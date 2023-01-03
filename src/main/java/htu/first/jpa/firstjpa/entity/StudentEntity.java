package htu.first.jpa.firstjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	Integer id;
	@Column(name = "name")
	@NotEmpty(message = "Name Cannot be Empty :)")
	String nameEntity;
	@Column(name = "age")
	@Min(message = "Age Caanot be less than 0", value = 0)
	@Max(message = "Age Caanot be grather  than 100", value = 100)
	Integer age;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	@JsonIgnore
	Dept deptObj;
	

}
