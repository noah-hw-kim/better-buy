public class Simple {
    private String var1;
    private SimpleTwo simpleTwo;
    public Simple() {
        var1 = "hello";
        simpleTwo = new SimpleTwo();
    }

    public class SimpleTwo {
        public String var2;
        public SimpleTwo() {
            var2 = "bye";
        }
    }
    public static void main(String[] args) {
        Simple simple = new Simple();
        System.out.println(simple.simpleTwo.var2);
    }
}
