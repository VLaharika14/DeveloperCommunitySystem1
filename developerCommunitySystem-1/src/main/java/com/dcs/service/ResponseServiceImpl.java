package com.dcs.service;

import java.util.ArrayList;

import java.util.List;

import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dcs.dao.ResponseDao;
import com.dcs.dto.ResponseDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.entity.Response;

@Service
public class ResponseServiceImpl implements IResponseService {

	@Autowired
	private ResponseDao responseDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override

	public ResponseDTO addResponse(ResponseDTO response) {
		Response entity1 = modelMapper.map(response, Response.class);
		entity1 = responseDao.save(entity1);
		return modelMapper.map(entity1, ResponseDTO.class);

	}

	@Override

	public ResponseDTO updateResponse(ResponseDTO response) {
		Response entity1 = modelMapper.map(response, Response.class);
		entity1 = responseDao.save(entity1);
		return modelMapper.map(entity1, ResponseDTO.class);

	}

	@Override

	public ResponseDTO removeResponse(Integer respId) {

		Response existingResponseEntity = responseDao.findById(respId)
				.orElseThrow(() -> new EntityNotFoundException("Response not found"));

		responseDao.deleteById(respId);

		return modelMapper.map(existingResponseEntity, ResponseDTO.class);

	}

	@Override
	public Page<ResponseDTO> getAllResponses(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Response> responsesPage = responseDao.findAll(pageable);
		
		Page<ResponseDTO> responseDto = responsesPage
				.map(re -> modelMapper.map(re, ResponseDTO.class));
		return responseDto;
//		List<ResponseDTO> responseDTOs = responsesPage.getContent().stream()
//				.map(entity -> modelMapper.map(entity, ResponseDTO.class)).collect(Collectors.toList());
//		return new PageImpl<>(responseDTOs, responsesPage.getPageable(), responsesPage.getTotalElements());
	}

	@Override
	public Integer getNoOfVotesOnResponseByVoteType(String voteType, Integer resId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Page<ResponseDTO> getResponseByDeveloper(Developer developer, Integer page, Integer size) {
//	    Pageable pageable1 = PageRequest.of(page, size);
//	    Page<Response> responsePage = responseDao.findByDevId(developer, pageable1);
//	    return responsePage.map(entity -> modelMapper.map(entity, ResponseDTO.class));
//	}

//	@Override
//
//	public Integer getNoOfVotesOnResponseByVoteType(String voteType, Integer resId) {
//
//		Integer number=responseDao.countByVoteTypeAndResId(voteType, resId);
//		return number;
//
//	}
//	@Override
//	public Page<ResponseDTO> getResponseByPost(Post post, Integer page, Integer size) {
//	    Pageable pageable = PageRequest.of(page, size);
//	    Page<Response> responsePage = responseDao.findByPostId(post, pageable);
//	    return responsePage.map(entity -> modelMapper.map(entity, ResponseDTO.class));
//	}
////	

//	@Override
//	public Page<ResponseDTO> getResponseByDeveloper(Developer developer, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
