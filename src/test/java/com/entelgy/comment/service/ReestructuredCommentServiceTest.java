package com.entelgy.comment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.entelgy.comment.model.Comment;
import com.entelgy.comment.model.ReestructuredComents;
import com.entelgy.comment.service.impl.ReestructuredCommentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReestructuredCommentServiceTest {

	@InjectMocks
	ReestructuredCommentServiceImpl reestructuredCommentService;

	@Mock
	RestTemplate restTemplate;

	@Test
	public void getReestructuredComments() {
		var testComment = new Comment(1, 2, "name", "email", "body");
		var myEntity = new ResponseEntity<List<Comment>>(Arrays.asList(testComment), HttpStatus.ACCEPTED);

		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.eq(null),
				Mockito.eq(new ParameterizedTypeReference<List<Comment>>() {
				}))).thenReturn(myEntity);

		ReestructuredComents reestructuredComents = reestructuredCommentService.getReestructuredComments();

		assertEquals(1, reestructuredComents.getData().size());
		assertEquals("1|2|email", reestructuredComents.getData().get(0));
	}

}
