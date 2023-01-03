package htu.first.jpa.firstjpa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import htu.first.jpa.firstjpa.pojo.Login;
import htu.first.jpa.firstjpa.pojo.Result;
import htu.first.jpa.firstjpa.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/login")
	public Result login(@RequestBody @Valid Login login) {

		return service.login(login);
	}

	@GetMapping("/test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader("token");
		System.out.println(token);
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
