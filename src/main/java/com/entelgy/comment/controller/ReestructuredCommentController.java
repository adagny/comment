package com.entelgy.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entelgy.comment.model.ReestructuredComents;
import com.entelgy.comment.service.ReestructuredCommentService;

@RestController
@RequestMapping("/comments")
public class ReestructuredCommentController {

	@Autowired
	ReestructuredCommentService reestructuredCommentService;

	@PostMapping("reestructureAll")
	public ReestructuredComents getReestructuredComment() {
		return reestructuredCommentService.getReestructuredComments();
	}

}
