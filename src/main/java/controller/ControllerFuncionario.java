/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import funcionarioDOA.FuncionarioDOA;
import java.sql.SQLException;
import java.util.List;
import model.Funcionario;
import model.IControllerFuncionario;

/**
 *
 * @author HP 255-G9
 */
public class ControllerFuncionario implements IControllerFuncionario{
    private FuncionarioDOA funcionarioDoa = new FuncionarioDOA();
    
    @Override
    public void setFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioDoa.createFuncionario(funcionario);
    }

    @Override
    public List<Funcionario> listFuncionario() throws SQLException {
        return funcionarioDoa.selectFuncionarios();
    }

    @Override
    public void updateFuncionario(Funcionario funcionario, int numero) throws SQLException {
        funcionarioDoa.updateFuncionario(funcionario,numero);
    }

    @Override
    public void deleteFuncionario(String numero) throws SQLException {
        funcionarioDoa.deleteFuncionario(numero);
    }

    @Override
    public Funcionario getFuncionario(Funcionario funcionario) throws SQLException {
        return funcionarioDoa.getFuncionario(funcionario);
    }

  
    
}
