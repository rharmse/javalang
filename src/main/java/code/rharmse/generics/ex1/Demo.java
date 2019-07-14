package code.rharmse.generics.ex1;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Demo {

    final EvenProperty property = new EvenProperty("Even numbers");

    public static void main(String...args) {
        Random rand = new Random(System.currentTimeMillis());
        List<Integer> data = new ArrayList<>(1000);

        for (int i = 0; i < 10000; i++) {
            data.add(rand.nextInt(10000));
        }

        NaturalNumbers numbers = new NaturalNumbers();
        numbers.process(data);
    }
}

abstract class Property<T> {
    private String propertyName;

    public Property() {
        this.propertyName = "";
    }

    public Property(String propertyName) {
        this.propertyName = propertyName;
    }

    public String toString() {
        return this.propertyName;
    }

    public abstract boolean test(T data);
}

class EvenProperty extends Property<Integer> {

    public EvenProperty(String property) {
        super(property);
    }

    public boolean test(Integer value) {
        return value % 2 == 0 ? true :  false;
    }
}

class OddProperty extends EvenProperty {

    public OddProperty(String property) {
        super(property);
    }

    @Override
    public boolean test(Integer value) {
        return !super.test(value);
    }
}

class PalindromeProperty extends Property<Integer> {
    public PalindromeProperty(String property) {
        super(property);
    }

    public boolean test(Integer value) {
        char[] digits = value.toString().toCharArray();

        boolean isPalindrome = true;
        int midPoint = digits.length % 2 == 0 ? digits.length/2 : (digits.length-1)/2;

        for (int i = 1; i <= midPoint && isPalindrome;  i++) {
            if (digits[i-1] != digits[digits.length - i]) {
                isPalindrome = false;
            }
        }
        isPalindrome &= digits.length > 2;
        return isPalindrome;
    }
}

abstract class PropertyCounter<T> {
    private int count;
    private Property<T> properties;

    public PropertyCounter(Property<T> prop) {
        this.properties = prop;
    }

    public int count(Collection<? extends T> data) {
        for (T item : data) {
            if (properties.test(item)) {
                count++;
            }
        }
        return count;
    }

    public String toString() {
        return new StringBuilder()
            .append(count)
            .append(" samples showed the ")
            .append(properties)
            .append(" property.")
            .toString();
    }
}

class EvenNumberCounter extends PropertyCounter<Integer> {

    public EvenNumberCounter(Property<Integer> prop) {
        super(prop);
    }
}

class OddNumberCounter extends PropertyCounter<Integer> {

    public OddNumberCounter(Property<Integer> prop) {
        super(prop);
    }
}

class PalindromeCounter extends PropertyCounter<Integer> {

    public PalindromeCounter(Property<Integer> prop) {
        super(prop);
    }
}

class NaturalNumbers {
    private List<PropertyCounter<Integer>> counters;
    
    public NaturalNumbers() {
        counters = new ArrayList<>();
        counters.add(new EvenNumberCounter(new EvenProperty("Even Number")));
        counters.add(new OddNumberCounter(new OddProperty("Odd Number")));
        counters.add(new PalindromeCounter(new PalindromeProperty("Pallindrome")));
    }

    public void process(Collection<Integer> data) {
        for (PropertyCounter<Integer> counter : counters) {
            counter.count(data);
            System.out.println(counter);
        }
    }
}