package com.dcs.dao;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dcs.entity.Developer;

@Repository
public interface DeveloperDao extends JpaRepository<Developer, Integer> {

	Page<Developer> findByStatus(String status, Pageable pageable);

	List<Developer> findByReputation(Integer reputation);

}