package com.dcs.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcs.dto.CommentDTO;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.ICommentService;

@RestController

@RequestMapping("/comment")

public class CommentController {

	@Autowired
	ICommentService commentService;

	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDTO> addComment(@Validated @RequestBody CommentDTO response)
			throws DeveloperCommunitySystemException {

		CommentDTO newResponse = commentService.addComment(response);
		if (newResponse == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CommentDTO>(newResponse, HttpStatus.OK);

	}

	@PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<CommentDTO> editResponse(@RequestBody CommentDTO comment)
			throws DeveloperCommunitySystemException {

		CommentDTO updateResponse = commentService.updateComment(comment);
		if (updateResponse == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CommentDTO>(updateResponse, HttpStatus.OK);

	}

	@DeleteMapping("/remove/{Id}")
	public ResponseEntity<String> removeComment(@PathVariable Integer Id) {

		CommentDTO isDelete = commentService.removeComment(Id);
		if (isDelete == null) {
			return new ResponseEntity<String>("Response not deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Response deleted", HttpStatus.OK);
	}

	@GetMapping(path = "get/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDTO> getByCommentId(@PathVariable Integer commentId)
			throws DeveloperCommunitySystemException {
		if (commentId == null) {
			throw new DeveloperCommunitySystemException("Invalid Comment ID");
		} else {
			CommentDTO response = commentService.getByCommentId(commentId);
			return new ResponseEntity<CommentDTO>(response, HttpStatus.OK);
		}
	}

}