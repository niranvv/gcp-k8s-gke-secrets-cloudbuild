package com.poc.firestore.controller;

import com.poc.firestore.entity.Question;
import com.poc.firestore.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class FirestoreController {
    @Autowired
    FirestoreService firestoreService;

    @GetMapping("/getQuestions")
    public List<Question> getAllQuestions() {
        List<Question> questionList = null;
        try {
            questionList = firestoreService.getQuestions();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionList;
    }
    @GetMapping("/addQuestion")
    public String addQuestion() {
        String addQuestion=null;
        try {
            addQuestion = firestoreService.addQuestion();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addQuestion;
    }
}
