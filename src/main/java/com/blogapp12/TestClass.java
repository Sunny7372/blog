package com.blogapp12;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestClass {
    public static void main(String[]args){
        List<Employee> data= Arrays.asList(
                new Employee(1,"adam",4000),
                new Employee(2,"stallin",50000),
                new Employee(3,"mike",6000)


        );
        Map<Integer, List<Employee>> collect = data.stream().collect(Collectors.groupingBy(e -> e.getSalary()));
        System.out.println(collect);


    }
}
