package com.example.coursework2.entities;

import com.example.coursework2.exceptions.QuestionAlreadyExistException;
import com.example.coursework2.exceptions.QuestionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class JavaQuestionRepositoryTest {
    private JavaQuestionRepository out;

    @BeforeEach
    void setUp() {
        out = new JavaQuestionRepository();
        out.init();
    }

    @Test
    void initTest() {
        assertThat(out.getAll())
                .hasSize(6)
                .isEqualTo(Set.of(
                        new Question("String это объект?", "Да"),
                        new Question("Heap это куча?", "Да"),
                        new Question("Spring это фреймворк?", "Да"),
                        new Question("Java это язык программирования?", "Да"),
                        new Question("Инкапсуляция это кофе в капсулах?", "нет"),
                        new Question("private это тип переменной?", "Нет")));
    }

    @Test
    void addPositiveTest() {
        out.add(new Question("Yes?", "Yes"));
        out.add(new Question("Yes?", "No"));
        out.add(new Question("Yes?", "Maybe"));

        assertThat(out.getAll())
                .isNotEmpty()
                .hasSize(9)
                .isEqualTo(Set.of(
                        new Question("String это объект?", "Да"),
                        new Question("Heap это куча?", "Да"),
                        new Question("Spring это фреймворк?", "Да"),
                        new Question("Java это язык программирования?", "Да"),
                        new Question("Инкапсуляция это кофе в капсулах?", "нет"),
                        new Question("private это тип переменной?", "Нет"),
                        new Question("Yes?", "Yes"),
                        new Question("Yes?", "No"),
                        new Question("Yes?", "Maybe")));

        assertThat(out.add(new Question("No?", "No")))
                .isNotNull()
                .isEqualTo(new Question("No?", "No"));
    }

    @Test
    void addNegativeTest() {
        out.add(new Question("Yes?", "Yes"));
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> out.add(new Question("Yes?", "Yes")));
    }

    @Test
    void removePositiveTest() {
        Question expected1 = new Question("Инкапсуляция это кофе в капсулах?", "нет");
        Question expected2 = new Question("private это тип переменной?", "Нет");

        out.remove(expected1);

        assertThat(out.getAll())
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(Set.of(
                        new Question("String это объект?", "Да"),
                        new Question("Heap это куча?", "Да"),
                        new Question("Spring это фреймворк?", "Да"),
                        new Question("Java это язык программирования?", "Да"),
                        new Question("private это тип переменной?", "Нет")));

        assertThat(out.remove(expected2))
                .isNotNull()
                .isEqualTo(expected2);
    }

    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> out.remove(new Question("Yes?", "Yes")));
    }
}