/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        // The reason why the test failed is because the Integer class caches the values from -128 to 127.
        // Unlike int, Integer is an object, so == compares the reference, not the value.
        // So when we compare two Integer objects with the same value out of range, the result is false.
        // To fix this, we can use the equals method to compare the values of the two objects.
        return a.equals(b);
    }
}
