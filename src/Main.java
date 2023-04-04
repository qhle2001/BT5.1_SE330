import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        int port = 9999; // Replace with the desired port number

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while (true) {
                // Wait for two clients to connect
                Socket clientSocketA = serverSocket.accept();
                System.out.println("Accepted connection from client A");

                Socket clientSocketB = serverSocket.accept();
                System.out.println("Accepted connection from client B");

                // Create input and output streams for the sockets
                InputStream inA = clientSocketA.getInputStream();
                OutputStream outB = clientSocketB.getOutputStream();

                InputStream inB = clientSocketB.getInputStream();
                OutputStream outA = clientSocketA.getOutputStream();

                // Read the filename from client A
                BufferedReader reader = new BufferedReader(new InputStreamReader(inA));
                String filename = reader.readLine();
                System.out.println("Received filename from client A: " + filename);

                // Send the filename to client B
                PrintWriter writer = new PrintWriter(outB, true);
                writer.println(filename);
                System.out.println("Sent filename to client B: " + filename);

                // Read the file contents from client B and send it to client A
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inB.read(buffer)) != -1) {
                    outA.write(buffer, 0, bytesRead);
                }
                System.out.println("File transfer complete");

                // Close the streams and sockets
                inA.close();
                outA.close();
                inB.close();
                outB.close();
                clientSocketA.close();
                clientSocketB.close();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
