/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP 255-G9
 */
public interface IControllerFuncionario {
    void setFuncionario(Funcionario funcionario) throws SQLException;
    List<Funcionario> listFuncionario() throws SQLException;
    void updateFuncionario(Funcionario funcionario, int numero) throws SQLException;
    void deleteFuncionario(String numero) throws SQLException;
    Funcionario getFuncionario(Funcionario funcionario) throws SQLException;
    
}
