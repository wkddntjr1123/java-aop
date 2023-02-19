package jang.aop;

import jang.aop.entity.Exam;
import jang.aop.entity.JangExam;

public class Program {
    public static void main(String[] args) {
        Exam exam = new JangExam(80, 100, 90, 100);
        exam.total();
    }
}