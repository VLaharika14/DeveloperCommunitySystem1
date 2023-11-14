package com.dcs.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dcs.dto.VoteDTO;

@Service
public interface IVoteService {

	VoteDTO addVote(VoteDTO vote);

	Page<VoteDTO> getAllVotes(int page, int size);

}
