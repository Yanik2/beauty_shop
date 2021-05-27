package com.example.beauty_shop.service.defaultimpl;

import com.example.beauty_shop.dao.mysql.FeedbackDaoImpl;
import com.example.beauty_shop.entity.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class FeedbackServiceImplTest {
    @Mock
    private FeedbackDaoImpl feedbackDaoMock;
    @InjectMocks
    private FeedbackServiceImpl testingInstance;

    @Test
    public void shouldReturnListOfFeedbacks() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(feedbackDaoMock.findAll()).thenReturn(new ArrayList<>());
        List<Feedback> feedbacks = testingInstance.getAllFeedbacks();
        Assertions.assertTrue(feedbacks.isEmpty());
    }

}