package com.dcs.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dcs.dto.CommentDTO;
@Service
public interface ICommentService {

	CommentDTO addComment(CommentDTO  comment);

	CommentDTO  updateComment(CommentDTO  comment);

	CommentDTO  removeComment(Integer respId);

	Integer getNoOfVotesOnCommentByVoteType(String  voteType, Integer commentId);

	CommentDTO  getByCommentId(Integer commentId);

	List<CommentDTO> getCommentsByPostId(Integer postId, int page, int pageSize);

	List<CommentDTO> getCommentsByResponseId(Integer resId, int pageNumber, int pageSize);
}