import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client_UDP {
    public static void main(String[] args) throws Exception {
        String host = "100.122.123.123";
        int port = 5051;

        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(host);

        Scanner scanner = new Scanner(System.in);

        byte[] buffer = new byte[1024];

        while (true) {
            System.out.print("Client: ");
            String mesaj = scanner.nextLine();

            byte[] sendData = mesaj.getBytes(StandardCharsets.UTF_8);
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, address, port);

            socket.send(sendPacket);

            if (mesaj.equalsIgnoreCase("exit")) {
                break;
            }

            DatagramPacket receivePacket =
                    new DatagramPacket(buffer, buffer.length);

            socket.receive(receivePacket);

            String raspuns = new String(
                    receivePacket.getData(),
                    0,
                    receivePacket.getLength(),
                    StandardCharsets.UTF_8
            );

            if (raspuns.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Server: " + raspuns);
        }

        socket.close();
        scanner.close();
        System.out.println("Client oprit.");
    }
}