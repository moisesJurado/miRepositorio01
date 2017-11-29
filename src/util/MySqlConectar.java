package util;
import java.sql.Connection;
import java.sql.DriverManager;
public class MySqlConectar {
	//crear un mètodo que retorne la conexiòn con la base de datos sis_biblioteca
	public Connection getConectar(){
		Connection cn=null;
		try {
			//reconocer el jar de manera lògica
			Class.forName("com.mysql.jdbc.Driver");
			//cadena de conexiòn
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/modelofinal","root","mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
	
}
