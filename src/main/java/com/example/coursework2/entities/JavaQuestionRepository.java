package com.example.coursework2.entities;

import com.example.coursework2.exceptions.QuestionAlreadyExistException;
import com.example.coursework2.exceptions.QuestionNotFoundException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questionSet;

    public JavaQuestionRepository() {
        questionSet = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        questionSet.addAll(Set.of(
                new Question("String это объект?", "Да"),
                new Question("Heap это куча?", "Да"),
                new Question("Spring это фреймворк?", "Да"),
                new Question("Java это язык программирования?", "Да"),
                new Question("Инкапсуляция это кофе в капсулах?", "нет"),
                new Question("private это тип переменной?", "Нет")));
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
