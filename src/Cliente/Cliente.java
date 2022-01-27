/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Cliente {

    static final int port = 2000;
    static final String HOST = "localhost";

    public static void main(String[] args) throws UnknownHostException, IOException {

        try {
            byte[] cadena = new byte[100];
            //Dirección del socket
            InetAddress maquina = InetAddress.getByName(HOST);
            DatagramSocket sSocket = new DatagramSocket();

            while (true) {
                System.out.println("Introduce datos. ");
                Scanner teclado = new Scanner(System.in);
                String datos = teclado.next();
                cadena = datos.getBytes();
                //args[0].getBytes();
                String asterisco = "*";
                if (!datos.contains(asterisco)) {
                    DatagramPacket paquete = new DatagramPacket(cadena, cadena.length,
                            maquina, port);
                    // Envío
                    sSocket.send(paquete);

                    // REcibe respuesta
                    byte[] cadena2 = new byte[100];
                    DatagramPacket recepcion = new DatagramPacket(cadena2,
                            cadena2.length, maquina, port);
                    sSocket.receive(recepcion);
                    //Lo guardamos en un String
                    String respuesta = new String(recepcion.getData());
                    System.out.println("Respuesta del servidor: " + respuesta);
                } else {
                                        DatagramPacket paquete = new DatagramPacket(cadena, cadena.length,
                            maquina, port);
                    // Envío
                    sSocket.send(paquete);

                    // REcibe respuesta
                    byte[] cadena2 = new byte[100];
                    DatagramPacket recepcion = new DatagramPacket(cadena2,
                            cadena2.length, maquina, port);
                    sSocket.receive(recepcion);
                    //Lo guardamos en un String
                    String respuesta = new String(recepcion.getData());
                    System.out.println("Respuesta del servidor: " + respuesta);
                    sSocket.close();
                break;
                }
            }

        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
