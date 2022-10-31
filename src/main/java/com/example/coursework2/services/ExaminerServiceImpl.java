package com.example.coursework2.services;

import com.example.coursework2.entities.Question;
import com.example.coursework2.exceptions.NotEnoughQuestionsException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService mathQuestionService;
    private final QuestionService javaQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService mathQuestionService, QuestionService javaQuestionService) {
        this.mathQuestionService = mathQuestionService;
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> temp = new HashSet<>();
        if (amount < javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) {
            while (temp.size() != amount) {
                if (random.nextBoolean()) {
                    temp.add(javaQuestionService.getRandom());
                } else {
                    temp.add(mathQuestionService.getRandom());
                }
            }
        } else throw new NotEnoughQuestionsException();
        return temp;
    }
}
