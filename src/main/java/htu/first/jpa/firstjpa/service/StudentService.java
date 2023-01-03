package htu.first.jpa.firstjpa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import htu.first.jpa.firstjpa.entity.StudentEntity;
import htu.first.jpa.firstjpa.pojo.Result;
import htu.first.jpa.firstjpa.repository.StudentRepo;

@Service
public class StudentService {
	@Autowired
	private StudentRepo repo;

	public Result addOrUpdateStudent(StudentEntity student) {
		Result result = new Result();
		Map<String, Object> mapResult = new HashMap<>();
		if (student.getNameEntity() == null || student.getNameEntity().isEmpty()) {
			result.setStatus("Failed");
			mapResult.put("NameEntity", "Cannot Send Name Entity Empty");
			result.setResultMap(mapResult);
			return result;
		}
		if (student.getId() != null) {
			if (student.getId() < 0)
				result.setStatus("Failed");
			mapResult.put("Id", "Cannot Send Id negative");
			result.setResultMap(mapResult);
			return result;
		}
		repo.save(student);
		result.setStatus("Succ");
		result.setResultMap(mapResult);
		return result;

	}

	public Boolean deleteStudentById(Integer id) {
		if (id == null || id < 0)
			return false;
		repo.deleteById(id);
		return true;
	}

	public Result findStudentById(Integer id) {
		Result result = new Result();
		Map<String, Object> mapResult = new HashMap<>();
		if (id == null || id < 0) {
			result.setStatus("Failed");
			mapResult.put("id", "Cannot send id null or empty");
			result.setResultMap(mapResult);
			return result;
		}
		result.setStatus("Succ");
		mapResult.put("Obj", repo.findById(id).orElse(new StudentEntity()));
		result.setResultMap(mapResult);
		return result;
	}

	public List<StudentEntity> findAllStudent() {
		return repo.findAll();
	}

	public StudentEntity findByName(String name) {
		if (name == null || name.isEmpty())
			return new StudentEntity();
		return repo.findByNameEntity(name);
	}

	public List<StudentEntity> findByNameV2(String name) {
		if (name == null || name.isEmpty())
			return new ArrayList<>();
		return repo.findByNameV2(name);
	}

	public List<StudentEntity> findByNameV3(String name) {
		if (name == null || name.isEmpty())
			return new ArrayList<>();
		return repo.findByNameV3(name);
	}

	public List<StudentEntity> findByAge(Integer age) {
		if (age == null || age < 0)
			return new ArrayList<>();
		return repo.findByAge(age);
	}
}
