package htu.first.jpa.firstjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import htu.first.jpa.firstjpa.entity.StudentEntity;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {

	StudentEntity findByNameEntity(String nameEntity);
//Using method Syntax
	List<StudentEntity> findByAge(Integer age);
//Using JPQL
	@Query(value = "SELECT e FROM StudentEntity e where e.nameEntity=:name")
	List<StudentEntity> findByNameV2(String name);
//Using Native Query
	@Query(nativeQuery = true, value = "SELECT * FROM student  where name=:name")
	List<StudentEntity> findByNameV3(String name);
}
