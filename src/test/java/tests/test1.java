package tests;

import org.junit.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test1 {

  /*
  using streams in the java we can perform aggregate operations on the data return from
  collection classes like Array List Set List
  Streams? Lambda expressions?
  */

  //Count the number of names starting with alphabet A in list
  @Test
  public void regular() {
    ArrayList<String> names = new ArrayList<String>();
    names.add("Anna");
    names.add("Dmitriy");
    names.add("Alexey");
    names.add("Alexander");
    names.add("Rita");
    int count = 0;

    for(int i = 0;i < names.size();i++) {
         String actual = names.get(i);
         if (actual.startsWith("A")) {
           count++;
         }
    }
    System.out.println(count);
  }

  @Test
  public void streamFilter() {
    ArrayList<String> names = new ArrayList<String>();
    names.add("Anna");
    names.add("Dmitriy");
    names.add("Alexey");
    names.add("Alexander");
    names.add("Rita");

    //the life of this filter..exist only in particular terminal operations
    //don't have count  - not any output
    //list is not modified
    //there is no life for intermediate operation if there is no terminal
    //terminal operation will execute only if interm op (filter) returns true
    Long c = names.stream().filter(s->s.startsWith("A")).count();
    System.out.println(c);

    //We have create the stream. You need not ArrayList collection
    //How to use filter in Stream API
    long d = Stream.of("Anna","Dmitriy","Alexey","Alexander","Rita").filter(s->{
            s.startsWith("A");
            return true;
    }).count();
    System.out.println(d);
    //print all the names of ArrayList
    //names.stream().filter(s -> s.length()>4).forEach(s -> System.out.println(s));
    names.stream().filter(s -> s.length()>4).limit(1).forEach(s -> System.out.println(s));
  }

  @Test
  public void streamMap() {
      ArrayList<String> names = new ArrayList<String>();
      names.add("man");
      names.add("Don");
      names.add("women");
      names.add("Alexander");
      names.add("Rita");


    //manipulate the string
    //print names which have last letter as "a" with UpperCase
    //modificate!! after filter
    Stream.of("Anna","Dmitriy","Alexey","Alexander","Rita").filter(s -> s.endsWith("a")).map(s->s.toUpperCase())
    .forEach(s -> System.out.println(s));
    //print names which have first letter as a with uppercase and sorted

    //Arrays.asList(); <-- Array as ArrayList <-- Stream
    //sorted() - terminal operation
    //limit() and count() too you need this if stream() and then filter()

    List<String> names1 = Arrays.asList("Anna","Dmitriy","Alexey","Alexander","Rita");
    names1.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase())
    .forEach(s -> System.out.println(s));
    //Merging two different lists
    //We want convert it back to the list - collect
    Stream<String> newStream = Stream.concat(names.stream(), names1.stream());
    //newStream.sorted().forEach(s -> System.out.println(s));
    boolean flag = newStream.anyMatch(s -> s.equalsIgnoreCase("Anna"));
    Assert.assertTrue(flag);
  }

  @Test
  public void streamCollect() {
    //collect results and convert it back into any list
    //new list
    List <String> ls = Stream.of("Rita","Dmitriy","Alexey","Alexander","Anna").filter(s -> s.endsWith("a")).map(s->s.toUpperCase())
    .collect(Collectors.toList());
    System.out.println(ls.get(0));
    //it is first result
    //another way in streams for 1 first result
    //not     .forEach(s -> System.out.println(s));
    //without taking into list
    // .limit


    List<Integer> values = Arrays.asList(3,2,2,7,5,1,9,7);
    //print unique numbers from this array
    //sort the array - 3rd index. limit we now how to use (first 3 meanings)
    //forEach() - all output
    //compare arrays using looping - 1 way
    //stream - 2 way
    // values.stream().distinct().forEach(s -> System.out.println(s));

    //you can use convert to list and than to collect

    List<Integer> li = values.stream().distinct().sorted().collect(Collectors.toList());
    System.out.println(li.get(2));
  }

}
