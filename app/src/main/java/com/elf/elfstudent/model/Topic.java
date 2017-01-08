package com.elf.elfstudent.model;


/**
 * Created by nandhu on 20/10/16.
 *
 */



public class Topic {

    private String topicName;
    private String points;
    private String questionAsked;
    private String correctanswer;


    public Topic(String name, String percentage, String questionAsked, String correctAnswer) {
        this.topicName = name;
        this.points = percentage;
        this.questionAsked = questionAsked;
        this.correctanswer = correctAnswer;



    }

    public Topic(String s) {
        this.topicName = s;
    }

    public String getQuestionAsked() {
        return questionAsked;
    }

    public void setQuestionAsked(String questionAsked) {
        this.questionAsked = questionAsked;
    }

    public String getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}



