package com.company;


public class SubjectImplement implements Subject{

    public Observer observer;

    @Override
    public void notifyObserver() {
        observer.update();
    }

    public void changeTurn() {

        notifyObserver();
    }

}
