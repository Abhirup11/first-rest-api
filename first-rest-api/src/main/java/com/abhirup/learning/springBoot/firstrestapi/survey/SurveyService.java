package com.abhirup.learning.springBoot.firstrestapi.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
public class SurveyService {

	private static List<Survey> surveys = new ArrayList<>();

	 final static Logger logger = (Logger) LoggerFactory.getLogger(SurveyService.class); 
	static {

		Question question1 = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2", "Fastest Growing Cloud Platform",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3", "Most Popular DevOps Tool",
				Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey", "Description of the Survey", questions);

		surveys.add(survey);

	}

	public List<Survey> retriveAllSurveys() {
		logger.info("Retrieve all surveys : START");
		return surveys;
	}

	public Survey retriveSurveyById(String surveyId) {

		/*
		 * Predicate<? super Survey> predicate=survey->survey.getId().equals(surveyId);
		 * Optional<Survey>
		 * optionalSurvey=surveys.stream().filter(predicate).findFirst();
		 * if(optionalSurvey.isEmpty()) return null; return optionalSurvey.get();
		 */
		logger.info("Retrieve survey by ID : START");
		for (Survey survey : surveys) {
			if (survey.getId().equals(surveyId)) {
				return survey;
			}
		}
		return null;

	}

	public List<Question> retriveQuestionsBySurveyId(String surveyId) {
		logger.info("retriveQuestionsBySurveyId START");
		for (Survey survey : surveys) {
			if (survey.getId().equals(surveyId)) {
				logger.info("retriveQuestionsBySurveyId START");
				return survey.getQuestions();
			}
		}
		return null;
	}

	public Question retriveQuestionsBySurveyIdAndQuestionId(String surveyId, String questionId) {
		List<Question> QuestionsBySurveyId = retriveQuestionsBySurveyId(surveyId);
		if(QuestionsBySurveyId==null) {
			return null;
		}
		for(Question question:QuestionsBySurveyId) {
			if(question.getId().equals(questionId)) {
				return question;
			}
		}
		return null;
	}

	public String addNewQuestionBySurveyId(String surveyId, Question question) {
		
		List<Question> retriveQuestionsBySurveyId = retriveQuestionsBySurveyId(surveyId);
//		question.setId(generateQuestionId());
		
		if(retriveQuestionsBySurveyId==null)
					return null;
		else {
			retriveQuestionsBySurveyId.add(question);
		}
		return question.getId();
	}

	private String generateQuestionId() {
		SecureRandom secureRandom=new SecureRandom();
		String questionId = (new BigInteger(32, secureRandom)).toString();
		return questionId;
	}

	public Question removeQuestionsBySurveyIdAndQuestionId(String surveyId, String questionId) {
		
		Question exactMatchingQuestion = retriveQuestionsBySurveyIdAndQuestionId(surveyId, questionId);
		if(exactMatchingQuestion==null) {
			return null;
		}
		Iterator<Question> QuestionsIterator = retriveQuestionsBySurveyId(surveyId).iterator();
		while(QuestionsIterator.hasNext()) {
			Question question = QuestionsIterator.next();
			if(question.getId().equals(questionId)) {
				QuestionsIterator.remove();
			}
		}
		return exactMatchingQuestion;
	}

	public String updateSurveyQuestion(String surveyId, Question question, String questionId) {
		List<Question> retriveQuestionsBySurveyId = retriveQuestionsBySurveyId(surveyId);
		Iterator<Question> questions = retriveQuestionsBySurveyId.iterator();
		while(questions.hasNext()) {
			if(questions.next().getId().equals(questionId)) {
//				question.setId(questionId);
				logger.info("Found questionId {} in the SurveyId {}",questionId,surveyId);
				questions.remove();
				logger.info("Successfully removed.");
			}
		}
		return(addNewQuestionBySurveyId(surveyId, question));
		
	}

}
