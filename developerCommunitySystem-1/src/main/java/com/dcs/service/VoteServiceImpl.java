package com.dcs.service;


 
import java.util.List;

import java.util.stream.Collectors;
 
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
 
import com.dcs.dao.VoteDao;

import com.dcs.dto.VoteDTO;

import com.dcs.entity.Vote;
 
@Service

public class VoteServiceImpl implements IVoteService {

	@Autowired

	VoteDao voteDao;

	@Autowired

	private ModelMapper modelMapper;
 
	

	public VoteDTO addVote(VoteDTO vote) {

		Vote entity1=modelMapper.map(vote, Vote.class);

		entity1= voteDao.save(entity1);

		return modelMapper.map(entity1, VoteDTO.class);

	}
 
	

	public Page<VoteDTO> getAllVotes(int page,int pageSize) {

		Pageable pageable = PageRequest.of(page, pageSize);

	    Page<Vote> votePage = voteDao.findAll(pageable);     

	    List<VoteDTO> voteDTOs = votePage.getContent().stream().map(entity -> modelMapper.map(entity, VoteDTO.class)).collect(Collectors.toList());     

	    return new PageImpl<>(voteDTOs, votePage.getPageable(), votePage.getTotalElements());
	    }
 
	}
 
