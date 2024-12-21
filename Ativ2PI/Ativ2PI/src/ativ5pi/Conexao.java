package ativ5pi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
     private Connection conexao;
    
    private static Connection conn = null;
    
    
    public static Connection getConexao() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/cenaflix";
                String user = "root";
                String password = "oooo";
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão estabelecida com sucesso.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao estabelecer conexão: " + e.getMessage()); } }
        return conn; }
    
    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql//localhost/A5pi","root","oooo");
            System.out.println("Sucesso de conexão");
        } catch( ClassNotFoundException e){
            System.out.println("Falha ao carregar a classe de conexao");
        } catch (SQLException e) {
            System.out.println("Falha ao conectar com o banco. Erro de SQL");} }
    
    public void desconectar() {
        try {
            if(conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("DESCONECTADO COM SUCESSO!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar");
        }
    }
    
    
    
    
    
    
}
