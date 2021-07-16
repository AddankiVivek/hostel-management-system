package com.vikash;

public class Shape extends Type {

    public Shape(){
        System.out.println("Inside a child class");
    }

    @Override
    public void differ() {
        System.out.println("This is the implementation for the abstarct method");
    }

    final void blue(){
            System.out.println("final method");
        }

}
