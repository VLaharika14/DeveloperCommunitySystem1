package com.dcs.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dcs.dao.DeveloperDao;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.IDeveloperService;

@RestController
@RequestMapping("/developer")
public class DeveloperController {
	@Autowired
	IDeveloperService developerService;
	@Autowired
	DeveloperDao developerDao;

	@GetMapping(path = "get/{devId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DeveloperDTO> getDevelopers(@PathVariable Integer devId)
			throws DeveloperCommunitySystemException {
		if ((Integer) devId == null) {
			throw new DeveloperCommunitySystemException("Invalid Developer ID");
		}
		DeveloperDTO developer1 = developerService.getDeveloperById(devId);
		return new ResponseEntity<DeveloperDTO>(developer1, HttpStatus.OK);
	}

	@PostMapping(path = "add/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DeveloperDTO> saveDeveloper(@RequestBody DeveloperDTO developer)
			throws DeveloperCommunitySystemException {
		DeveloperDTO newDeveloper = developerService.addDeveloper(developer);
		if (newDeveloper == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DeveloperDTO>(newDeveloper, HttpStatus.OK);
	}
	@GetMapping(path = "get1/{devId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<PostDTO>> getAllPostsByDeveloper(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, int devId)
			throws DeveloperCommunitySystemException {
		if ((Integer) devId == null) {
			throw new DeveloperCommunitySystemException("Invalid Developer ID");
		}
		Page<PostDTO> developer5 = developerService.getPostsByDeveloper(page, size, devId);
		return new ResponseEntity<Page<PostDTO>>(developer5, HttpStatus.OK);

	}

	@GetMapping(path = "get3/{reputation}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeveloperDTO>> getDevelopersByReputation(@PathVariable Integer reputation) {
		List<DeveloperDTO> developer7 = developerService.getDeveloperByReputation(reputation);
		return new ResponseEntity<List<DeveloperDTO>>(developer7, HttpStatus.OK);
	}

	@GetMapping(path = "get22/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<DeveloperDTO>> getDevelopersByStatus(@PathVariable String status, Pageable pageable

	) {
		Page<DeveloperDTO> developerPage = developerService.getDevelopersByStatus(status, pageable);
		return ResponseEntity.ok(developerPage);
	}

	@PutMapping(path = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DeveloperDTO> editDeveloper(@RequestBody DeveloperDTO developer)
			throws DeveloperCommunitySystemException {
		DeveloperDTO developer1 = developerService.updateDeveloper(developer);
		if (developer1 == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DeveloperDTO>(developer1, HttpStatus.OK);
	}

	@DeleteMapping(path = "/remove")
	public ResponseEntity<String> removeDeveloper(@PathVariable Integer devId) {
		DeveloperDTO isDelete = developerService.removeDeveloper(devId);
		if (isDelete == null) {
			return new ResponseEntity<String>("Response not deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Response deleted", HttpStatus.OK);
	}

	@GetMapping("/allDevelopers")
	public List<DeveloperDTO> readAllDevelopers() {
		return developerService.viewDevelopers();
	}

	@DeleteMapping("/remove/{devId}")
	public String delete(@PathVariable(value = "devid") Integer userId) {
		return developerService.delete(userId);
	}
}
