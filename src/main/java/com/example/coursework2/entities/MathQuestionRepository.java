package com.example.coursework2.entities;

import com.example.coursework2.exceptions.QuestionAlreadyExistException;
import com.example.coursework2.exceptions.QuestionNotFoundException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> questionSet;

    public MathQuestionRepository() {
        questionSet = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        questionSet.addAll(Set.of(
                new Question("2 + 2", "4"),
                new Question("2 * 2", "4"),
                new Question("-2^2", "4"),
                new Question("4!", "24"),
                new Question("√25", "5"),
                new Question("16 / 4", "4")));
    }

    @Override
    public Question add(Question question) {
        if (questionSet.contains(question)) {
            throw new QuestionAlreadyExistException();
        }

        questionSet.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionSet.contains(question)) {
            throw new QuestionNotFoundException();
        }

        questionSet.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(questionSet);
    }
}
