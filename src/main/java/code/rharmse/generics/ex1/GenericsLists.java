package code.rharmse.generics.ex1;

import java.util.List;
import java.util.ArrayList;

class Lists {

    public static int sum(List<Integer> ints) {
        int sum = 0;
        for (int e : ints) {
            sum += e;
        }
        return sum;
    }

    @SafeVarargs
    public static <Any> List<Any> toList(Any... arr) {
        List<Any> list = new ArrayList<Any>(arr.length);
        for ( Any el : arr) list.add(el);
        return list;
    }

    public void addToList(List<? super Number> list, Number value) {
        assert list != null && value != null;
        list.add(value);
    }

    public Number getFromList(List<? extends Number> list, Number index) {
        assert list != null && index != null;
        return list.get(index.intValue());
    }

    public static void main(String...args) {
        List<Integer> ints = Lists.toList();
        List<Object> objs = Lists.<Object>toList(1, "two");
    }
}