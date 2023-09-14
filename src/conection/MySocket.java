package conection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MySocket extends DatagramSocket {

    public MySocket(int port) throws SocketException {
        super(port);
    }

    public MySocket(int port, InetAddress laddr) throws SocketException {
        super(port, laddr);
    }

    public void sendRequest(Request request){
        try{
            String message = request.getCode() + "," + request.getContent();
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
                    data,
                    data.length,
                    this.getInetAddress(),
                    this.getPort()
            );
            this.send(sendPacket);
        }catch (IOException error){
            throw new RuntimeException(error);
        }
    }
}
