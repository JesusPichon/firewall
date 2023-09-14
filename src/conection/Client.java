package conection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client extends MySocket{

    private InetAddress serverAddress;

    public Client(int port) throws SocketException {
        super(port);
    }

    public Client(int port, InetAddress serverAddress) throws SocketException {
        super(port);
        this.serverAddress = serverAddress;
    }

    @Override
    public void sendRequest(Request request) {
        try{
            String message = request.getCode() + "," + request.getContent();
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
                    data,
                    data.length,
                    this.serverAddress,
                    this.getPort()
            );
            this.send(sendPacket);
        }catch (IOException error){
            throw new RuntimeException(error);
        }
    }

    public static void main(String[] args) {
        try {

            Client client = new Client(5000, InetAddress.getByName("localhost"));
            client.sendRequest(new Request(100, "Programa.txt"));

        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
