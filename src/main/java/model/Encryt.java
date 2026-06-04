/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP 255-G9
 */

import org.mindrot.jbcrypt.BCrypt;

public class Encryt {
    
    public static String encriptarContrasena(String contrasenaPlana) {
        return BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt());
    }

    public static boolean verificarContrasena(String contrasenaPlana, String hashAlmacenado) {
        try {
            // BCrypt se encarga de extraer la sal del hash almacenado para compararlos
            return BCrypt.checkpw(contrasenaPlana, hashAlmacenado);
        } catch (IllegalArgumentException e) {
            // Maneja el caso de que el hash almacenado tenga un formato inválido
            return false;
        }
    }
}
