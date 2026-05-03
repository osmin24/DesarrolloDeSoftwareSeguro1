/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.iudigital.proyecto.ds.iudigital;

import funcionarioDOA.FuncionarioDOA;
import java.sql.SQLException;
import java.util.List;
import model.Funcionario;

/**
 *
 * @author HP 255-G9
 */
public class ProyectoDsIudigital {

    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario();
        FuncionarioDOA funcionariodoa = new FuncionarioDOA();
        try{
            System.out.println("-------------Antigua registro");

            List<Funcionario> funcionarios = funcionariodoa.selectFuncionarios();
            funcionarios.stream().forEach(x -> System.out.println(x.getNombres()));
            
            System.out.println("-------------Nuevo registro");
           /** funcionario.setAnos(4);
            funcionario.setNombres("Bolivia Peru");
            funcionario.setApellidos("Escobar locas");
            funcionario.setTipoDocumento("Pasaporte");
            funcionario.setNumeroDocumento("5435");
            funcionario.setNivelEducativo("Profesional");
            funcionariodoa.createFuncionario(funcionario);
            **/
          
            
            System.out.println("-------------Delete registro");
            //funcionario.setNumeroDocumento("5435");
            //funcionariodoa.deleteFuncionario(funcionario.getNumeroDocumento());
            System.out.println("-------------update registro");
            funcionario.setNombres("Bolivia Per");
            funcionario.setApellidos("Escobar los");
            funcionario.setTipoDocumento("Cedula");
            funcionario.setNivelEducativo("Tecnico");
            int numero = 1;
  

        }catch(SQLException error){
            System.out.println("Error de base de datos   "+error.toString());
        }
    }
}
