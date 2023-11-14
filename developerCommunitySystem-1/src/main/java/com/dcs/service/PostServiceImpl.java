package com.dcs.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dcs.dao.PostDao;
import com.dcs.dao.VoteDao;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.entity.Vote;
@Service
public class PostServiceImpl implements IPostService {
	@Autowired
	PostDao postDao;
	@Autowired
	VoteDao voteDao;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	
	public PostDTO addPost(PostDTO post) {
		Post entity1=modelMapper.map(post, Post.class);
		entity1=postDao.save(entity1);
		PostDTO entity=modelMapper.map(entity1,PostDTO.class);
		return entity;
	}

	@Override
	public PostDTO updatePost(PostDTO post) {
		Post entity1=modelMapper.map(post, Post.class);
		entity1=postDao.save(entity1);
		PostDTO entity=modelMapper.map(entity1,PostDTO.class);
		return entity;
	}


	@Override
	public PostDTO getPostById(Integer postId) {
		Post entity=postDao.findById(postId).orElse(null);
		PostDTO entity1=modelMapper.map(entity,PostDTO.class);
		return entity1;
		
		
	}

	@Override
	public PostDTO removePost(Integer postId) {
		Post post = postDao.findById(postId).orElse(null);
        if (post != null) {
            postDao.delete(post);
            PostDTO entity1=modelMapper.map(post,PostDTO.class);
            return entity1;
        }
        return modelMapper.map(post,PostDTO.class);
	}

	
//	//	@Override
//		public List<PostDTO> getPostsByDate(LocalDateTime date) {
//		    List<Post> matchingPosts = postDao.findPostsByDate(date);
//	 
//		    List<PostDTO> postDTO = matchingPosts.stream()
//		            .map(entity -> modelMapper.map(entity, PostDTO.class))
//		            .collect(Collectors.toList());
//	 
//		    return postDTO;
//		}

	
	
//	 @Override
//	    public List<PostDTO> findByTopic(String topic, int page, int pageSize) {
//	        Pageable pageable = PageRequest.of(page, pageSize);
//	        Page<Post> postPage = postDao.findByTopic(topic, pageable);
//
//	        return postPage.getContent()
//	                .stream()
//	                .map(post -> modelMapper.map(post, PostDTO.class))
//	                .collect(Collectors.toList());
//	    }
	 @Override
	    public List<PostDTO> findByTopic(String topic) {
	        //Pageable pageable = PageRequest.of(page, pageSize);
	        List<Post> post = postDao.findByTopic(topic);

	        return post
	                .stream()
	                .map(post1 -> modelMapper.map(post1, PostDTO.class))
	                .collect(Collectors.toList());
	    }
	 

	@Override
	public List<PostDTO> viewPost() {
			List<PostDTO> postDTOs = new ArrayList<PostDTO>();
			List<Post> post = postDao.findAll();

			for (Post post1 : post) {
				PostDTO postDTO = new PostDTO();
				//postDTO.setDeveloper(post1.getDeveloper());
				//postDTO.setListOfResponse(post1.getListOfResponse());
				postDTO.setNoOfViews(post1.getNoOfViews());
				postDTO.setPostDateTime(post1.getPostDateTime());
                //postDTO.setDeveloper(mapPostTODTO(post1.getDeveloper()));
                //postDTO.setDeveloper(mapPostTODTO(post1.getDeveloper()));
                
				postDTO.setPostId(post1.getPostId());
				postDTO.setQuery(post1.getQuery());
				postDTO.setTopic(post1.getTopic());
				
				postDTOs.add(postDTO);
			}
			return postDTOs;
		}

//	private DeveloperDTO mapPostTODTO(Developer developer) {
//		DeveloperDTO developer1 = modelMapper.map(developer, DeveloperDTO.class);
//		return developer1;
//	}

	}
	