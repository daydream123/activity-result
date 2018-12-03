package com.feizhang.activityresult.sample.validate;


import com.feizhang.activityresult.sample.validate.annotations.Len;
import com.feizhang.activityresult.sample.validate.annotations.Min;
import com.feizhang.activityresult.sample.validate.annotations.NotNull;

public class People {

//    @NotNull(message = "children is null")
//    public List<Child> children = new ArrayList<>();

    @NotNull(field = "array")
    public Child[] array;

    public static class Child {
        @NotNull(field = "name")
        @Len(field = "age", len = 4)
        public String name;

        @Min(field = "age", min = 10)
        public int age;

        public Child(String name, int age){
            this.name = name;
            this.age = age;
        }
    }
}
