package code.rharmse.generics.ex1;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collection;

public class Demo {

    final EvenProperty property = new EvenProperty("Even numbers");

    public static void main(String...args) {
        char[] digits = new Integer(1213121).toString().toCharArray();

        boolean isPalindrome = true;
        int midPoint = digits.length % 2 == 0 ? digits.length/2 : (digits.length-1)/2;

        for (int i = 1; i <= midPoint && isPalindrome;  i++) {
            if (digits[i-1] != digits[digits.length - i]) {
                isPalindrome = false;
            }
        }
        System.out.println("Is Pallindrome: " + isPalindrome);
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

class PalindromProperty extends Property<Integer> {
    public PalindromProperty(String property) {
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

class NaturalNumbers {
    private List<Property<Integer>> properties;
    public NaturalNumbers() {
        properties = new ArrayList<>();
        properties.add(new EvenProperty("Even Number"));
        properties.add(new OddProperty("Odd Number"));


    }
}