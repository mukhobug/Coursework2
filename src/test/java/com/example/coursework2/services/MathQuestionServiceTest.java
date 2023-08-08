package com.example.coursework2.services;

import com.example.coursework2.entities.Question;
import com.example.coursework2.entities.QuestionRepository;
import com.example.coursework2.exceptions.NotEnoughQuestionsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    @Mock
    private QuestionRepository mathQuestionRepository;
    @InjectMocks
    private MathQuestionService out;

    @Test
    void addByStringsTest() {
        Question expected = new Question("da", "da");
        when(mathQuestionRepository.add(expected))
                .thenReturn(expected);

        assertThat(out.add("da", "da"))
                .isEqualTo(expected);
    }

    @Test
    void AddByObjectTest() {
        Question expected = new Question("da", "da");
        when(mathQuestionRepository.add(expected))
                .thenReturn(expected);

        assertThat(out.add(expected))
                .isEqualTo(expected);
    }

    @Test
    void removeTest() {
        Question expected = new Question("Yes?", "Yes");
        when(mathQuestionRepository.remove(expected))
                .thenReturn(expected);

        assertThat(out.remove(expected))
                .isEqualTo(expected);
    }

    @Test
    void getRandomQuestionPositiveTest() {
        when(mathQuestionRepository.getAll()).thenReturn(Set.of(
                new Question("Yes?", "Yes"),
                new Question("Yes?", "No"),
                new Question("Yes?", "Maybe")));

        assertThat(out.getRandom())
                .isNotNull();
        assertThat(out.getAll())
                .isNotEmpty()
                .hasSize(3)
                .contains(out.getRandom());
    }

    @Test
    void getRandomQuestionNegativeTest() {
        when(mathQuestionRepository.getAll()).thenReturn(Collections.emptySet());

        assertThatExceptionOfType(NotEnoughQuestionsException.class)
                .isThrownBy(() -> out.getRandom());
    }
}