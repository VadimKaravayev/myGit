


public class Play {
    public static void main(String[] args) {
        Play play = new Play();
        A a = new A();
        System.out.println(a.getI());
        play.setI(a);
        System.out.println(a.getI());


    }

    public void setI(A a) {
        a.i = 10;
    }
}


class A {
    int i = 9999;

    public int getI() {
        return i;
    }


}