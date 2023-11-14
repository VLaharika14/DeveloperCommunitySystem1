package com.dcs.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.dcs.dao.DeveloperDao;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.exception.DeveloperCommunitySystemException;

@Service
public class DeveloperServiceImpl implements IDeveloperService {
	@Autowired
	DeveloperDao developerDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DeveloperDTO addDeveloper(DeveloperDTO developer) {
		Developer entity1 = modelMapper.map(developer, Developer.class);
		entity1 = developerDao.save(entity1);
		return modelMapper.map(entity1, DeveloperDTO.class);

	}

	@Override
	public DeveloperDTO getDeveloperById(Integer devId) {
		Optional<Developer> entity3 = developerDao.findById(devId);
		return modelMapper.map(entity3, DeveloperDTO.class);

	}

	@Override
	public List<DeveloperDTO> getDeveloperByReputation(Integer reputation) {
		List<Developer> entity4 = developerDao.findByReputation(reputation);
		List<DeveloperDTO> developerDTOs = entity4.stream().map(entity -> modelMapper.map(entity, DeveloperDTO.class))
				.collect(Collectors.toList());
		return developerDTOs;
	}

	@Override
	public Page<PostDTO> getPostsByDeveloper(Integer page, int size, int devId) {
		Developer developer = developerDao.findById(devId).orElse(null);
		Pageable pageable = PageRequest.of(page, size);
		if (developer != null) {
			List<Post> entity6 = developer.getListOfPosts();
			int pageSize = pageable.getPageSize();
			int currentPage = pageable.getPageNumber();
			int startItem = currentPage * pageSize;
			List<Post> pagedEntityList;
			if (startItem < entity6.size()) {
				int endItem = Math.min(startItem + pageSize, entity6.size());
				pagedEntityList = entity6.subList(startItem, endItem);
			} else {
				pagedEntityList = Collections.emptyList();
			}
			List<PostDTO> developerDTOs = pagedEntityList.stream().map(entity -> modelMapper.map(entity, PostDTO.class))
					.collect(Collectors.toList());
			return new PageImpl<>(developerDTOs, PageRequest.of(currentPage, pageSize), entity6.size());
		} else {
			return new PageImpl<>(Collections.emptyList());
		}
	}

	@Override
	public Page<DeveloperDTO> getAllDevelopers(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Developer> developersPage = developerDao.findAll(pageable);
		List<DeveloperDTO> developerDTOs = developersPage.getContent().stream()
				.map(entity -> modelMapper.map(entity, DeveloperDTO.class)).collect(Collectors.toList());
		return new PageImpl<>(developerDTOs, developersPage.getPageable(), developersPage.getTotalElements());
		
	}

	@Override
	public Page<DeveloperDTO> getDevelopersByStatus(String status, Pageable pageable) {
		Page<Developer> developersPage = developerDao.findByStatus(status, pageable);
		List<DeveloperDTO> developerDTOs = developersPage.getContent().stream()
				.map(entity -> modelMapper.map(entity, DeveloperDTO.class)).collect(Collectors.toList());
		return new PageImpl<>(developerDTOs, developersPage.getPageable(), developersPage.getTotalElements());

	}

	@Override
	public DeveloperDTO updateDeveloper(DeveloperDTO developer) {
		Developer entity1 = modelMapper.map(developer, Developer.class);
		entity1 = developerDao.save(entity1);
		DeveloperDTO entity = modelMapper.map(entity1, DeveloperDTO.class);
		return entity;
	}

	@Override
	public DeveloperDTO removeDeveloper(Integer devId) {
		Developer developer = developerDao.findById(devId).orElse(null);
		if (developer != null) {
			developerDao.delete(developer);
			DeveloperDTO entity1 = modelMapper.map(developer, DeveloperDTO.class);
			return entity1;
		}
		return modelMapper.map(developer, DeveloperDTO.class);
	}

	public List<DeveloperDTO> viewDevelopers() {
		List<DeveloperDTO> developerDTOs = new ArrayList<DeveloperDTO>();
		List<Developer> developer = developerDao.findAll();

		for (Developer developer1 : developer) {
			DeveloperDTO developerDTO = new DeveloperDTO();
			developerDTO.setDevName(developer1.getDevName());
			developerDTO.setDevSkill(developer1.getDevSkill());
			developerDTO.setMemberSince(developer1.getMemberSince());
			developerDTO.setReputation(developer1.getReputation());

			developerDTO.setUserId(developer1.getUserId());
			developerDTO.setUserName(developer1.getUserName());
			developerDTO.setUserPassword(developer1.getUserPassword());
			developerDTO.setUserRole(developer1.getUserRole());
			developerDTOs.add(developerDTO);
		}
		return developerDTOs;
	}

	@Override
	public String delete(Integer id) {
		Developer developer;
		try {
			developer = developerDao.findById(id)
					.orElseThrow(() -> new DeveloperCommunitySystemException("Developer not found"));
		
			developerDao.deleteById(id);

		} catch (DeveloperCommunitySystemException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Developer Not found";

		}
		return "Developer Deleted Successfully";
	}

}
