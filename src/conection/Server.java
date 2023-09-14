package conection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends MySocket implements Runnable, ReceiveRequests {

    public Server(int port) throws SocketException {
        super(port);
        System.out.println("Servidor listo!!\nEn espera de peticiones...");
    }

    @Override
    public void run() {
        byte[] receiveData = new byte[1024];
        while (true) {
            receiveMessages(receiveData);
        }
    }

    @Override
    public void receiveMessages(byte[] data) {
        while(true) {
            try {
                DatagramPacket receivePacket = new DatagramPacket(data, data.length);
                this.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress address = receivePacket.getAddress();
                System.out.println("Mensaje recibido: " + message + " direccion: " + address);

            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        try {
            Server server = new Server(5000);
            Thread threadServer = new Thread(server);
            threadServer.start();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
