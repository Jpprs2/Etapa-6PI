package ativ5pi;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ValoresDAO {
    
    
    public static boolean cadastrar(Valores v) throws SQLException {
        try {
            //Conexão com o banco
            Conexao c = new Conexao();
            c.conectar();

            //Instrução SQL que será executada
            String sql = "INSERT INTO listprod(nome, preco, quantidade, estado, categoria, vendido, numeroNotaFiscal, dataDaCompra) "
                      + "VALUES(?,?,?,?,?);";

            //Usar a string e transformar em SQL usando PreparedStatement
            PreparedStatement query = c.getConexao().prepareStatement(sql);
            query.setString(1, v.getOqueé());
            query.setString(2, v.getOrigem());
            query.setString(3, v.getQuantidade());
            query.setString(4, v.getFornecedor());
            query.setString(5, v.getComprador());

            //Executar a instrução SQL
            query.execute();

            //Desconectar do banco
            c.desconectar();
            return true;
        } catch (SQLException se) {
            System.out.println("Erro ao cadastrar registro no banco de dados");
            return false; } }
     
    
     
     public static List<Valores> listarTodos() {
        //Declaração da variável lista que será retornada
        List<Valores> lista = new ArrayList<Valores>();

        try {
            //Conectar ao banco
            Conexao c = new Conexao();
            c.conectar();

            //Instrução SQL
            String sql = "SELECT * FROM produto";
            PreparedStatement consulta = c.getConexao().prepareStatement(sql);

            //Executar a instrução SQL e pegar os resultados
            //ResultSet -> Classe do Java que armazena os resultados de uma QUERY (busca) feita em SQL
            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                Valores v = new Valores();

                v.setOqueé(resposta.getString("O que é"));
                v.setOrigem(resposta.getString("Origem"));
                v.setQuantidade(resposta.getString("Quantidade"));
                v.setFornecedor(resposta.getString("Fornecedor"));
                v.setComprador(resposta.getString("Comprador"));
                
                lista.add(v);
            }

            //Desconectar do banco
            c.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao listar os registros do banco de dados!");
        }
        return lista; }
     
     
     
     public static Valores buscarPorId(int id) {
        Valores v = new Valores();

        try {
            Conexao c = new Conexao();
            c.conectar();

            //Instrução SQL
            String sql = "SELECT * FROM produto WHERE id=?";
            PreparedStatement consulta = c.getConexao().prepareStatement(sql);
            consulta.setInt(1, id);
            
            //Executar a instrução SQL
            ResultSet resposta = consulta.executeQuery();
            
            if (resposta.next()) {
                v.setOqueé(resposta.getString("O que é"));
                v.setOrigem(resposta.getString("Origem"));
                v.setQuantidade(resposta.getString("Quantidade"));
                v.setFornecedor(resposta.getString("Fornecedor"));
                v.setComprador(resposta.getString("Comprador"));
            }
            
            //Desconectar do banco
            c.desconectar();
        }catch(SQLException e) {
            System.out.println("Erro ao buscar o registro " + id + " do banco de dados");
        }
        return v;}
     
     
     
     public static boolean atualizar(Valores v) {
        try {
            Conexao c = new Conexao();
            c.conectar();

            //Instrução SQL
            String sql = "UPDATE produto SET nome=?, preco=?, quantidade=?, estado=?, categoria=?, vendido=?, numeroNotaFiscal=?, dataDaCompra=? WHERE id=?;";
            PreparedStatement consulta = c.getConexao().prepareStatement(sql);

            //Passar as informações do objeto para a consulta
            consulta.setString(1, v.getOqueé());
            consulta.setString(2, v.getOrigem());
            consulta.setString(3, v.getOrigem());
            consulta.setString(4, v.getFornecedor());
            consulta.setString(5, v.getComprador());
            
            //Executar a instrução
            consulta.execute();

            //Desconectar do Banco
            c.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o registro do banco de dados");
            return false;} }
    
    
}
