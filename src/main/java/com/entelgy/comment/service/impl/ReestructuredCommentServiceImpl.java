package com.entelgy.comment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entelgy.comment.model.Comment;
import com.entelgy.comment.model.ReestructuredComents;
import com.entelgy.comment.service.ReestructuredCommentService;

@Service
public class ReestructuredCommentServiceImpl implements ReestructuredCommentService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ReestructuredComents getReestructuredComments() {
		ReestructuredComents reestructuredComents = new ReestructuredComents();
		ResponseEntity<List<Comment>> comments = restTemplate.exchange("https://jsonplaceholder.typicode.com/comments",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {
				});
		reestructuredComents.setData(comments.getBody().stream().map(c -> {
			return reestructureComment(c);
		}).collect(Collectors.toList()));

		return reestructuredComents;
	}

	private String reestructureComment(Comment comment) {
		StringBuilder formatedComment = new StringBuilder();
		formatedComment.append(comment.getPostId());
		formatedComment.append("|");
		formatedComment.append(comment.getId());
		formatedComment.append("|");
		formatedComment.append(comment.getEmail());
		return formatedComment.toString();
	}
}
