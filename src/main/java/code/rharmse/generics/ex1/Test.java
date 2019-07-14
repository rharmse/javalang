
package code.rharmse.generics.ex1;

import java.lang.reflect.Method;

class Printer<T>
{
    public void printArray(T[] array) {
        assert array!=null;
        for (T item: array) {
            System.out.println(item);
        }
    }
 
}

public class Test {


    public static void main( String args[] ) {
        Printer<Object> myPrinter = new Printer<Object>();
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);
        int count = 0;

        for (Method method : Printer.class.getDeclaredMethods()) {
            String name = method.getName();

            if(name.equals("printArray"))
                count++;
        }

        if(count > 1)System.out.println("Method overloading is not allowed!");
      
    }
}