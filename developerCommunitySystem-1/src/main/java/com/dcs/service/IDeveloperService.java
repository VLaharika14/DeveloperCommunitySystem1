package com.dcs.service;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.exception.DeveloperCommunitySystemException;
@Service
public interface IDeveloperService {

	DeveloperDTO addDeveloper(DeveloperDTO developer);


	DeveloperDTO getDeveloperById(Integer devId);

	List<DeveloperDTO> getDeveloperByReputation(Integer reputation);

	


	Page<DeveloperDTO> getDevelopersByStatus(String status, Pageable pageable);


	Page<PostDTO> getPostsByDeveloper(Integer page, int size, int devId);


	DeveloperDTO updateDeveloper(@Valid DeveloperDTO developerDto) throws DeveloperCommunitySystemException;


	DeveloperDTO removeDeveloper(Integer devId);
	
	Page<DeveloperDTO> getAllDevelopers(int page, int size);


	List<DeveloperDTO> viewDevelopers();


	String delete(Integer id);







}