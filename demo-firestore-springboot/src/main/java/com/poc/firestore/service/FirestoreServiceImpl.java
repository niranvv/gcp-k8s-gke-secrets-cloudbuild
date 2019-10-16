package com.poc.firestore.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.poc.firestore.entity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FirestoreServiceImpl implements FirestoreService {
    @Override
    public List<Question> getQuestions() throws Exception{
        Firestore db = getFirestore();
        List<Question> questions = getQuestions(db);
        return questions;
    }

    private Firestore getFirestore() {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        //.setProjectId("opportune-mile-246823")
                        .build();
        return firestoreOptions.getService();
    }

    private List<Question> getQuestions(Firestore db) throws InterruptedException, java.util.concurrent.ExecutionException {
        // asynchronously retrieve all questions
        ApiFuture<QuerySnapshot> query = db.collection("questions").get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        List<Question> questions =new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Question question =new Question();
            question.setQuestionId(document.getString("questionId"));
            question.setQuestionText(document.getString("questionText"));
            question.setQuestionMandatory(document.getBoolean("questionMandatory"));
            question.setSortOrder(document.getLong("sortOrder"));
            questions.add(question);
        }
        return questions;
    }
    @Override
    public String addQuestion() throws Exception {
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection("categories").document("category1");
// Add document data with an additional field ("middle")
        Map<String, Object> data = new HashMap<>();
        data.put("questionId", "1");
        data.put("questionText", "Whats ur name");
        data.put("questionMandatory", false);
        data.put("sortOrder", 10);

        ApiFuture<WriteResult> result = docRef.set(data);
        return "Update time : " + result.get().getUpdateTime();
    }
}
