package com.abhirup.learning.springBoot.firstrestapi.survey;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.qos.logback.classic.Logger;

@RestController
public class SurveyResource {
	
	private SurveyService surveyService;

	
	public SurveyResource(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}
	
	//surveys
	@RequestMapping("/surveys")
	public List<Survey> retriveAllSurveys(){
		
		return surveyService.retriveAllSurveys();
	}

	
	@RequestMapping("/surveys/{surveyId}")
	public Survey retriveSurveyById(@PathVariable String surveyId){
		
		Survey retriveSurveyById = surveyService.retriveSurveyById(surveyId);
		if(retriveSurveyById==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return retriveSurveyById;
	}
	
	
	@RequestMapping("/surveys/{surveyId}/questions")
	public List<Question> retriveQuestionsBySurveyId(@PathVariable String surveyId){
		
		 List<Question> questionsBySurveyId = surveyService.retriveQuestionsBySurveyId(surveyId);
		if(questionsBySurveyId==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return questionsBySurveyId;
	}
	
	
	@RequestMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retriveQuestionsBySurveyIdAndQuestionId(@PathVariable String surveyId,
			@PathVariable String questionId) {

		Question retriveQuestionBySurveyIdAndQuestionId = surveyService
				.retriveQuestionsBySurveyIdAndQuestionId(surveyId, questionId);
		if (retriveQuestionBySurveyIdAndQuestionId == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return retriveQuestionBySurveyIdAndQuestionId;
	}
	
	
	
	@RequestMapping(value="/surveys/{surveyId}/questions",method = RequestMethod.POST)
	public ResponseEntity<Object> addNewQuestionBySurveyId(@PathVariable String surveyId, 
					@RequestBody Question question){
		
		String questionId= surveyService.addNewQuestionBySurveyId(surveyId,question);
		if(questionId==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").
				buildAndExpand(questionId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}",method=RequestMethod.DELETE)
	public ResponseEntity<Object> removeQuestionsBySurveyIdAndQuestionId(@PathVariable String surveyId,
			@PathVariable String questionId) {

		Question removedQuestionBySurveyIdAndQuestionId = surveyService
				.removeQuestionsBySurveyIdAndQuestionId(surveyId, questionId);
		if (removedQuestionBySurveyIdAndQuestionId == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}",method = RequestMethod.PUT)
	public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId,
					@RequestBody Question question){
		
		String questionid= surveyService.updateSurveyQuestion(surveyId,question,questionId);
		if(questionid==null) {
	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").
				buildAndExpand(questionId).toUri();
		return ResponseEntity.created(location).build();
	}
}
