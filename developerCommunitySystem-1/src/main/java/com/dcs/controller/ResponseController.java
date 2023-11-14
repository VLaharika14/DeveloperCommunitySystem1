package com.dcs.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.dcs.dto.ResponseDTO;
import com.dcs.service.IResponseService;

@RestController
@RequestMapping("/Response")
public class ResponseController {
	@Autowired
	IResponseService responseService;

	@PostMapping(path = "add/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> saveResponse(@RequestBody ResponseDTO response) {
		ResponseDTO newResponse = responseService.addResponse(response);
		return new ResponseEntity<ResponseDTO>(newResponse, HttpStatus.OK);
	}

	@PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> editResponse(@RequestBody ResponseDTO response) {
		ResponseDTO updateResponse = responseService.updateResponse(response);
		return new ResponseEntity<ResponseDTO>(updateResponse, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{respId}")
	public ResponseEntity<String> removeResponse(@PathVariable Integer respId) {
		ResponseDTO isDelete = responseService.removeResponse(respId);
		if (isDelete == null) {
			return new ResponseEntity<String>("Response not deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Response deleted", HttpStatus.OK);
	}

	@GetMapping(path = "/votes/{voteType}/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getNoOfVotesOnResponseByVoteType(String voteType, Integer resId) {
		Integer value = responseService.getNoOfVotesOnResponseByVoteType(voteType, resId);
		return new ResponseEntity<Integer>(value, HttpStatus.OK);
	}

	@GetMapping("get/all")
	public ResponseEntity<Page<ResponseDTO>> getAllDevelopers(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<ResponseDTO> entities = responseService.getAllResponses(page, size);
		return new ResponseEntity<Page<ResponseDTO>>(entities, HttpStatus.OK);
	}
}