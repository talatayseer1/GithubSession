package htu.first.jpa.firstjpa.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import htu.first.jpa.firstjpa.entity.User;
import htu.first.jpa.firstjpa.pojo.Login;
import htu.first.jpa.firstjpa.pojo.Result;
import htu.first.jpa.firstjpa.repository.UserRepo;
import htu.first.jpa.firstjpa.utility.TokenUtility;

@Service
public class UserService {
	@Autowired
	UserRepo repo;
	@Autowired
	private TokenUtility tokenUtility;

	public Result login(Login login) {
		Result result = new Result();
		Map<String, Object> mapResult = new HashMap<>();
		User user = repo.findByUsername(login.getUsername());
		if (user == null) {
			mapResult.put("user", "User Not Found :(");
			result.setStatus("Failed");
			result.setResultMap(mapResult);
			return result;
		}
		if (!user.getPassword().equalsIgnoreCase(login.getPassword())) {

			mapResult.put("password", "Inncorect Password :(");
			result.setStatus("Failed");
			result.setResultMap(mapResult);
			return result;

		}
		String token = tokenUtility.generateToken(login.getUsername());
		mapResult.put("token", token);
		result.setStatus("Succ");
		result.setResultMap(mapResult);
		return result;
	}

}
