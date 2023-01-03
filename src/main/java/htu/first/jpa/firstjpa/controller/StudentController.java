package htu.first.jpa.firstjpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import htu.first.jpa.firstjpa.entity.Dept;
import htu.first.jpa.firstjpa.entity.StudentEntity;
import htu.first.jpa.firstjpa.pojo.Result;
import htu.first.jpa.firstjpa.service.DeptService;
import htu.first.jpa.firstjpa.service.StudentService;
import htu.first.jpa.firstjpa.utility.TokenUtility;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService service;
	@Autowired
	private DeptService deptService;
	@Autowired
	private TokenUtility tokenUtility;

	@GetMapping("/findStudentById")
	public Result findUserById(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer userId) {
		Result result = tokenUtility.checkToken(request.getHeader("token"));
		if (result.getStatus().equalsIgnoreCase("Succ")) {
			return service.findStudentById(userId);
		} else {
			return result;
		}

	}

	@PostMapping("/addStudent")
	public Result addStudent(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody StudentEntity student) {
		Result result = tokenUtility.checkToken(request.getHeader("token"));
		if (result.getStatus().equalsIgnoreCase("Succ")) {
			return service.addOrUpdateStudent(student);
		} else {
			return result;
		}

	}

	@PutMapping("/updateStudent")
	public Result updateStudent(HttpServletRequest request, HttpServletResponse response,
			@RequestBody StudentEntity student) {
		Result result = tokenUtility.checkToken(request.getHeader("token"));
		if (result.getStatus().equalsIgnoreCase("Succ")) {
			return service.addOrUpdateStudent(student);
		} else {
			return result;
		}

	}

	@DeleteMapping("/deleteStudentById")
	public String deleteStudent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer studentId) {
		if (service.deleteStudentById(studentId))
			return "Succ";
		else
			return "Failed";
	}

	@GetMapping("/findAllStudent")
	public List<StudentEntity> findAllStudent(HttpServletRequest request, HttpServletResponse response) {
		return service.findAllStudent();
	}

	@GetMapping("/findStudentByName")
	public StudentEntity findUserByName(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String name) {

		return service.findByName(name);
	}

	@GetMapping("/findStudentByAge")
	public List<StudentEntity> findUsersByAge(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer age) {

		return service.findByAge(age);
	}

	@GetMapping("/findStudentByNameV2")
	public List<StudentEntity> findUserByNameV2(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String name) {

		return service.findByNameV2(name);
	}

	@GetMapping("/findStudentByNameV3")
	public List<StudentEntity> findUserByNameV3(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String name) {

		return service.findByNameV3(name);
	}

	@GetMapping("/findDeptById")
	public Dept findDeptById(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer userId) {

		return deptService.findDeptById(userId);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
		Result result = new Result();
		result.setStatus("Failed");

		Map<String, Object> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		result.setResultMap(errors);
		return result;

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Result handleAllExceptionMethod(Exception ex, WebRequest requset, HttpServletResponse res) {
		Result result = new Result();
		result.setStatus("Failed");

		Map<String, Object> errors = new HashMap<>();
		errors.put("Exception", ex.getCause());
		result.setResultMap(errors);
		return result;
	}

}
