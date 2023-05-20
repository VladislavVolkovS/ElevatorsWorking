package com.company;

import javax.swing.*;
import java.util.Random;
import java.util.TreeMap;

public class Main {
    static final int countFloor = 5;
    static final int countLift = 2;

    public static void main(String[] args) {

        Controller controller = new Controller();

        Thread lifts = new Thread(new Work(controller));

        Visitor aaa = new Visitor();
        lifts.start();

        for (int i = 0; i < 100; i++) {
            Visitor visitor = new Visitor();
            visitor.callLift(controller);
            //System.out.println(i+" "+visitor.fromFloor+" "+visitor.toFloor);

            try {
                Thread.sleep(2000);
                //System.out.println("added person");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        int a = 0;
    }

}