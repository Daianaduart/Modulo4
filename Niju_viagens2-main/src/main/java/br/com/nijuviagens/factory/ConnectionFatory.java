package br.com.nijuviagens.factory;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectionFatory {
	
    private static String URL = "jdbc:mysql://localhost:3306/niju.crud";
private static String USUARIO = "root";
private static String SENHA = "Dai142817";

public static ConnectionFatory createConnectionMySQL() throws Exception {
// Faz com que a classe seja carregada pela JVM
Class.forName("com.mysql.cj.jdbc.Driver");

// Cria conexão com banco de dados ---------- com senha
ConnectionFatory connection = (ConnectionFatory) DriverManager.getConnection(URL, USUARIO, SENHA);

return connection;
}

public static void main(String[] args) throws Exception {
// Recupera uma conexão com o banco de dados
ConnectionFatory con = createConnectionMySQL();

// testar a conexão 
if (con != null) {
System.out.println(con + "\n\n ****  Conexão obtida com sucesso!  ****");
con.clone();
}
}

public PreparedStatement prepareStatement(String sql) {
	// TODO Auto-generated method stub
	return null;
}



}
