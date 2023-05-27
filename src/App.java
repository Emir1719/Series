public class App
{
    public static void main(String[] args)
    {
        Series series1 = new Series();
        series1.addRange(0, 10, 2);
        series1.add(fun1());
        series1.list();
        Box box = new Box();
        System.out.println(box.s1.getFriend(0));
        System.out.println(series1.get(6));
    }

    public static int fun1() {
        return 0;
    }
}