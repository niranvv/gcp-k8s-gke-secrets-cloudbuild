package com.poc.firestore.service;

import com.poc.firestore.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FirestoreService {
    List<Question> getQuestions() throws Exception;
    String addQuestion() throws Exception;
}
