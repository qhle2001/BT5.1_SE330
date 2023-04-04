import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost"; // Replace with the hostname or IP address of the server
        int port = 9999; // Replace with the port number used by the server
        String filename = "C:/Users/DELL/OneDrive/Documents/CorpusLingistic/DataTest.txt"; // Replace with the name of the file to transfer

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server on port " + port);

            // Create input and output streams for the socket
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            // Send the filename to the server
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(filename);
            System.out.println("Sent filename to server: " + filename);

            // Read the file contents from the server and save it to a file
            FileOutputStream fileOut = new FileOutputStream(filename);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
            }
            System.out.println("File transfer complete");

            // Close the streams and socket
            fileOut.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
