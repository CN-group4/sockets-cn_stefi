import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String host = "100.122.123.123";
        int port = 5050;

        Socket socket = new Socket(host, port);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
        );

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
        );

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Client: ");
            String mesaj = scanner.nextLine();

            out.write(mesaj);
            out.newLine();
            out.flush();

            if (mesaj.equalsIgnoreCase("exit")) {
                break;
            }

            String raspuns = in.readLine();
            if (raspuns == null || raspuns.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Server: " + raspuns);
        }

        socket.close();
        scanner.close();
        System.out.println("Conexiune închisă.");
    }
}