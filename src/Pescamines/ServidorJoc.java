package Pescamines;

import java.io.*;
import java.net.*;

public class ServidorJoc {


    public static void main(String[] args) {

        ServidorJoc server = new ServidorJoc();
        final int port = 5000;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Joc Pescamines iniciat esperant client per que jugui:");
            DatagramSocket socketUDP = new DatagramSocket(port);
            while (true) {
                DatagramPacket peticio = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticio);

                server.rebenPeticio(peticio);

                int portClient = peticio.getPort();
                InetAddress direccio = peticio.getAddress();

                buffer = server.generarResposta();

                DatagramPacket resposta = new DatagramPacket(buffer, buffer.length, direccio, portClient);
                System.out.println("Envio al joc al client");
                socketUDP.send(resposta);
            }        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void rebenPeticio(DatagramPacket peticio){
        String misatge = new String(peticio.getData());
        System.out.println(misatge);
        System.out.println("Rebre al client al joc ");
    }
    byte[] generarResposta(){

        String misatge = "Comienza el juego/ Començi al joc";
        byte[] buffer = misatge.getBytes();
        return buffer;
    }
}
