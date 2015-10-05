import java.sql.SQLException;

import com.beyonic.database.DbHelper;
import com.beyonic.database.User;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			User user = DbHelper.getUser("samonkoba@gmail.com");
			
			System.out.println(user.getFull_name());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
