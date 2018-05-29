import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.UUID;

public class Play {
    public static void main(String[] args) {

        Cookie id = new Cookie("id", "12345");
        System.out.println(id.getName());

    }
}
