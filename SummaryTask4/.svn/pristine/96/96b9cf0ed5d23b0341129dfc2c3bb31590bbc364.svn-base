
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;
import javax.sql.DataSource;

import ua.karavayev.beans.Person;
import ua.karavayev.beans.Role;
import ua.karavayev.databasemanager.DBmanager;

public class Test {
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
			
			Set<Days> days = EnumSet.allOf(Days.class); 
			System.out.println(days);
			
			Iterator<Days> iter = days.iterator();
			while(iter.hasNext()) {
				System.out.println(iter.next());
			}
	}
}



enum Days {
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY;
}

	