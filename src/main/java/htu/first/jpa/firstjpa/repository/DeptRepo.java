package htu.first.jpa.firstjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import htu.first.jpa.firstjpa.entity.Dept;

@Repository
public interface DeptRepo extends JpaRepository<Dept, Integer> {

}
