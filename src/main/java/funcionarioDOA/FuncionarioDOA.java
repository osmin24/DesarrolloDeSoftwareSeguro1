/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionarioDOA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CRUD;
import model.Funcionario;
import databaseconfig.ConfigurationDB;
import model.Encryt;
/**
 *
 * @author HP 255-G9
 */
public class FuncionarioDOA implements CRUD{

    private static final String GET_DATA="SELECT * FROM public.funcionario;";
    
    @Override
    public void createFuncionario(Funcionario funcionario) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        System.out.println(funcionario.getNombres());

        String sql = "INSERT INTO public.funcionario(_id , numero_identificacion, nombres, apellidos, anos, rol, password, email, nivel_educativo, tipo_identificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = ConfigurationDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            String passwordHas = Encryt.encriptarContrasena(funcionario.getPassword());

            // Alerta: Estás repitiendo el NumeroDocumento tanto para el '_id' como para 'numero_identificacion'. 
            // Asegúrate de que el '_id' en Postgres acepte el formato de documento.
            preparedStatement.setInt(1, Integer.parseInt(funcionario.getNumeroDocumento())); 
            preparedStatement.setString(2, funcionario.getNumeroDocumento());
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setInt(5, funcionario.getAnos());
            preparedStatement.setString(6, funcionario.getRol());
            preparedStatement.setString(7, passwordHas);
            preparedStatement.setString(8, funcionario.getEmail());
            preparedStatement.setString(9, funcionario.getNivelEducativo());
            preparedStatement.setString(10, funcionario.getTipoDocumento());

            preparedStatement.executeUpdate();
            System.out.println("Funcionario registrado con éxito en la BD.");

        } catch(SQLException error) {
            System.out.println("Error en creacion de funcionario: " + error.toString());
        } finally {
            // CORRECCIÓN: Cada close() debe estar protegido con un try-catch
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }

            try {
                if (connection != null) connection.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }

            // Eliminé el resultSet.close() porque no lo estás usando en un INSERT
        }
    }

    @Override
    public List<Funcionario> selectFuncionarios() throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
           
            List<Funcionario> funcionarios = new ArrayList<>();
            connection = ConfigurationDB.getConnection();
            preparedStatement = connection.prepareStatement(GET_DATA);
            resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    
                    Funcionario funcionario = new Funcionario();
                    
                    funcionario.setTipoDocumento(resultSet.getString("tipo_identificacion"));
                    funcionario.setNumeroDocumento(resultSet.getString("numero_identificacion"));
                    funcionario.setNombres(resultSet.getString("nombres"));
                    funcionario.setApellidos(resultSet.getString("apellidos"));
                    funcionario.setAnos(resultSet.getInt("anos")); // Cambié "titulo_grado" por "anos" según tu interfaz
                    funcionario.setNivelEducativo(resultSet.getString("nivel_educativo"));
                    funcionario.setRol(resultSet.getString("rol"));
                    funcionario.setEmail(resultSet.getString("email"));
                    funcionario.setPassword(resultSet.getString("password"));   

                    funcionarios.add(funcionario);
                }
            return funcionarios;
        }finally{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
    }

    @Override
    public void updateFuncionario(model.Funcionario funcionario,int  _id) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            String sql = "UPDATE public.funcionario SET tipo_identificacion = ?, nombres = ?, "
               + "apellidos = ?, anos = ?, nivel_educativo = ? WHERE _id = ?";
            connection = ConfigurationDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            // Asignamos los valores en orden
            preparedStatement.setString(1, funcionario.getTipoDocumento());
            preparedStatement.setString(2, funcionario.getNombres());
            preparedStatement.setString(3, funcionario.getApellidos());
            preparedStatement.setInt(4, funcionario.getAnos());
            preparedStatement.setString(5, funcionario.getNivelEducativo());
            preparedStatement.setInt(6, _id);

            preparedStatement.executeUpdate();
               
        }finally{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
    }

    @Override
    public void deleteFuncionario(String numero) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            
            connection = ConfigurationDB.getConnection();
            preparedStatement = connection.prepareStatement("delete from public.funcionario where numero_identificacion ="+"'"+numero+"'"+";");
            preparedStatement.executeQuery();
               
        }
        finally{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
    }
    

    @Override
    public Funcionario getFuncionario(Funcionario funcionario) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM public.funcionario WHERE numero_identificacion = ?";
    
        try {
            connection = ConfigurationDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, funcionario.getNumeroDocumento());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
               
                funcionario.setTipoDocumento(resultSet.getString("tipo_identificacion"));
                funcionario.setNumeroDocumento(resultSet.getString("numero_identificacion"));
                funcionario.setNombres(resultSet.getString("nombres"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setAnos(resultSet.getInt("anos")); // Cambié "titulo_grado" por "anos" según tu interfaz
                funcionario.setNivelEducativo(resultSet.getString("nivel_educativo"));
                funcionario.setRol(resultSet.getString("rol"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setPassword(resultSet.getString("password"));
            } else {
                return null; 
            }
                          
        return funcionario;
        }finally{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
    }
    
}
