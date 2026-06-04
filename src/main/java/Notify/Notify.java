/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Notify;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.util.Random;
import model.Funcionario;
import model.INotify;
import redis.clients.jedis.Jedis;

/**
 *
 * @author HP 255-G9
 */
public class Notify implements INotify{

    @Override
    public int setNotifyUser(Funcionario funcionario) {
        Random random = new Random();
        
        String host = "sandbox.smtp.mailtrap.io";
        String port = ""; // También puede ser 587 o 465 según tu config de Mailtrap
        final String username = "";
        final String password = "";
        int minimo = 100;
        int maximo = 200;
        int codigo = random.nextInt((maximo - minimo) + 1) + minimo;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Habilita TLS
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // 2. Crear la sesión con autenticación
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 3. Crear el mensaje de correo
            Message message = new MimeMessage(session);
            
            // Remitente simulado
            message.setFrom(new InternetAddress("remitente@pruebas.com"));
            
            // Destinatario simulado (no le llegará a él, irá a Mailtrap)
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("destino_test@correo.com"));
            
            // Asunto y cuerpo del mensaje
            message.setSubject("Correo de Prueba desde NetBeans 19");
            message.setText("Hola,\n\nEste es un correo de prueba enviado desde una aplicación Java utilizando Jakarta Mail.\n\nCodigo "+codigo);

            // 4. Enviar el correo
            System.out.println("Enviando correo de prueba...");
            Transport.send(message);
            
            System.out.println("¡Correo enviado con éxito al servidor de pruebas!");
            return codigo;
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
        return codigo;
    }
    
     public void guardarCodigoRedis(String email,  String codigo) {
        // Conectar a Redis (localhost por defecto)
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            
            // Guardamos el email como clave y el código como valor
            jedis.set(email, codigo);
            
            // Hacemos que la clave expire automáticamente en 900 segundos (15 minutos)
            jedis.expire(email, 900); 
            
            System.out.println("Código guardado en Redis. Expira en 15 min.");
        }
    }
    
    public boolean verificarCodigo(String email, String codigoIngresado) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            String codigoCorrecto = jedis.get(email);
            
            // Si coincide, el código es válido
            return codigoCorrecto != null && codigoCorrecto.equals(codigoIngresado);
        }
    }
    
}
