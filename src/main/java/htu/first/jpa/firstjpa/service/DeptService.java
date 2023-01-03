package htu.first.jpa.firstjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import htu.first.jpa.firstjpa.entity.Dept;
import htu.first.jpa.firstjpa.repository.DeptRepo;

@Service
public class DeptService {
	@Autowired
	DeptRepo repo;

	public Dept findDeptById(Integer id) {
		if (id == null || id < 0)
			return new Dept();
		return repo.findById(id).orElse(new Dept());
	}

}
