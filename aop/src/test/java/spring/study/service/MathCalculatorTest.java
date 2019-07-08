package spring.study.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class MathCalculatorTest {

    @Autowired
    MathCalculator mathCalculator;

    @Test
    public void div() {
        int div = mathCalculator.div(2, 1);
        System.out.println(div);
    }

    @Test
    public void div0() {
        int div = mathCalculator.div(2, 0);
        System.out.println(div);
    }
}