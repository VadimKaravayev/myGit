import javax.servlet.http.Cookie;


public class Play {
    public static void main(String[] args) {

        Cookie id = new Cookie("id", "12345");
        System.out.println(id.getName());

    }
}
