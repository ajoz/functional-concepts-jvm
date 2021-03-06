package io.github.ajoz.iter;

public class IterTest {
    public static void main(String[] args) {
        /*
         It's possible to chain each Iter one by one just with proper passing
         of the reference, although it does not look as sexy as what we are
         used to with Stream API (Java) or Sequence API (Kotlin) or Rx it still
         works and is lazy. If the last call to forEach is commented out, this
         code won't process the given array of Strings.
         */
        final Iter<String> strings = new ArrayIter<>("This", "is", "a", "basic", "test");
        final Iter<String> peeks = new OnEachIter<>(strings, System.out::println);
        final Iter<Integer> mapped = new MapIter<>(peeks, String::length);
        final Iter<Integer> filtered = new FilterIter<>(mapped, length -> length > 2);
        final Iter<Integer> taken = new TakeIter<>(filtered, 2);
//        Iters.forEach(System.out::println, taken);


//        final Iter<Integer> iterator =
        Iter.from("This", "is", "a", "very", "basic", "test", "of", "Iter")
                .onEach(System.out::println)
                .map(String::length)
                .filter(len -> len > 2)
                .take(1);
        // .forEach(System.out::println); or passing the reference to Iter directly
        // Iters.forEach(System.out::println, iterator);

        /*
         This is a potentially infinite operation that might result with out
         of memory error.
         */
        final Iter<Integer> iterator =
                Iter.from(0, i -> i + 1)
                        .map(x -> x * 2)
                        .take(10);

        // although Iter<T> extends Iterable<T> .for live template does not work :(
        // for (final Integer integer : iterator) {
        //    System.out.println("item: " + integer);
        // }

        // Iter is very simple and it's not possible to traverse it multiple times
        // calling iterator.toList().size() will have different results
        System.out.println("#1 Elements in the iter: " + iterator.toList().size());
        System.out.println("#2 Elements in the iter: " + iterator.toList().size());
    }
}
