package code.rharmse.generics.ex1;

import java.util.Arrays;
import java.util.List;

class Assignments {

    public Assignments(){}

    public List<? extends Number> integerArrToNumList(Integer...data) {
        //Reify to int
        List<Integer> ints = Arrays.asList(data);
        //Assign to wildcarded subtype of Number
        List<? extends Number> nums = ints;
        //compilation fails due to reified value of ints list - confirming in unit test
        //nums.set(2, 3.14);               
        return nums;
    }

    public List<? super Integer> integerVarargToIntegerList(Integer...data) {
        List<Number> nums = Arrays.asList(data);
        return nums;
    }

    public List<? super Number> integerVarargToNumberList(Integer...data) {
        List<Number> nums = Arrays.asList(data);
        //List<Integer> nums = Arrays.asList(data); //--> This breaks due Integer not being superclass of number
        List<Object> numObjs = Arrays.asList((Object[])data);
        return nums != null ? nums : numObjs;
    }

    public <T> void addNumberToList(List<T> list, T obj ) {
        assert list != null;
        list.add(obj);
    }

    private class MyObject extends Object {
        public MyObject() {

        }
    }
}