package com.dcs.service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.dcs.dto.ResponseDTO;

@Service
@Qualifier("responseServiceImpl")
public interface IResponseService {

	ResponseDTO  addResponse(ResponseDTO  response);

	ResponseDTO  updateResponse(ResponseDTO  response);

	ResponseDTO  removeResponse(Integer respId);

	
	Integer getNoOfVotesOnResponseByVoteType(String  voteType, Integer resId);

	Page<ResponseDTO> getAllResponses(int page, int size);
	
	

	

	

	

//	Page<ResponseDTO> getResponseByDeveloper(Developer developer, Pageable pageable);
//
//
//	
//
//	
//
//	
//
//	Page<ResponseDTO> getResponseByDeveloper(Developer developer, Integer page, Integer size);
//
//	Page<ResponseDTO> getResponseByPost(Post post, Integer page, Integer size);
//
//	Page<ResponseDTO> getResponsesByPost(Post post, Pageable pageable);

	

	

	

	

	//Page<ResponseDTO> getResponseByPost(Integer postId, Integer page, Integer size);

	

	//Page<ResponseDTO> getResponseByDeveloper(Integer devId, Integer page, Integer size);

	

}