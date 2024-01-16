package main.java.k12集合.List.list创建及Lambda方法;

import java.util.ArrayList;
import java.util.List;

public class List创建方法 {

    public static void main(String[] args) {

        List<Model> list = new ArrayList<>(){{
            add(new Model(1,"名称"));
            add(new Model(2,"名称"));
            add(new Model(3,"名称"));
        }};

        for (Model model : list) {
            System.out.println(model);
        }




    }



}
