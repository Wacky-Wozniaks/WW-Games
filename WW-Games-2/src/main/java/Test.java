import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wackywozniaks.dao.impl.UserDAOImpl;
import com.wackywozniaks.entity.User;

public class Test {
	public static void main(String[] args) {
	      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	      UserDAOImpl userDAOImpl = (UserDAOImpl)context.getBean("UserDAOImpl");
	      List<User> users = userDAOImpl.listUsers();
	      for (User record : users) {
	          System.out.print("ID : " + record.getId() );
	          System.out.print(", Name : " + record.getFirstName() + " " + record.getLastName() );
	          System.out.println(", Email : " + record.getEmail());
	       }
	}
}
