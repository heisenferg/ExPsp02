/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Receptor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Receptor {
    static final int port = 2000;


    public static void main(String[] args) {
          
        String datoRecibido = null;

        
        try {
            //Creamos el socket
            DatagramSocket sSocket = new DatagramSocket(port);
            while (true) {
                // Espacio para los mensajes
                byte[] cadenaRecibida = new byte[20];
                DatagramPacket paquete = new DatagramPacket(cadenaRecibida, cadenaRecibida.length);
                System.out.println("Esperando mensaje");
                sSocket.receive(paquete);
                datoRecibido = new String (paquete.getData(),0,paquete.getLength());
                //Devolvemos mensaje
                int puertoEmisor = paquete.getPort();
                InetAddress direccionEmisor = paquete.getAddress();
                System.out.println("Cadena recibida: " + datoRecibido);
                String asterisco="*";
                String tamanio;
                // Comprobaciones
                if (!datoRecibido.contains(asterisco)){
                    tamanio = "El dato recibido, en mayúsculas es: " + datoRecibido.toUpperCase();
                    cadenaRecibida = tamanio.getBytes();
                    DatagramPacket respuesta = new DatagramPacket(cadenaRecibida, cadenaRecibida.length,
                    direccionEmisor, puertoEmisor);
                    sSocket.send(respuesta);
                }
                if (datoRecibido.contains(asterisco)){
                    System.out.println("Asterisco recibido, finaliza la conexión");
                    tamanio = "Finaliza la conexión, asterisco recibido.";
                    cadenaRecibida = tamanio.getBytes();
                    DatagramPacket respuesta = new DatagramPacket(cadenaRecibida, cadenaRecibida.length,
                    direccionEmisor, puertoEmisor);
                    sSocket.send(respuesta);
                    sSocket.close();
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
