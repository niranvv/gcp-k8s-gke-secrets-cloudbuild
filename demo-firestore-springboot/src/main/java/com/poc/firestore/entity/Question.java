package com.poc.firestore.entity;

public class Question {
    private String questionId;
    private String questionText;
    private boolean questionMandatory;
    private long sortOrder;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean getQuestionManadatory(){ return questionMandatory;}

    public void setQuestionMandatory(boolean questionMandatory){ this.questionMandatory=questionMandatory;}

    public long getSortOrder(){return sortOrder;}

    public void setSortOrder(long sortOrder) { this.sortOrder=sortOrder;}
}
