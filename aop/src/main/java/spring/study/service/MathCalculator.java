package spring.study.service;

import org.springframework.stereotype.Service;

@Service
public class MathCalculator {
    public int div(int i,int j){
        System.out.println("MathCalculator...div...");
        return i/j;
    }
}
