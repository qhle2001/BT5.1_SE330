import java.io.*;
import java.net.*;

public class client2 {
    public static void main(String[] args) {
        String host = "localhost"; // Replace with the hostname or IP address of the server
        int port = 9999; // Replace with the port number used by the server
        String filename = "C:/Users/DELL/OneDrive/Documents/CorpusLingistic/DataTest.txt/"; // Replace with the desired name of the file to receive

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server on port " + port);

            // Create input and output streams for the socket
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // Read the filename from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String receivedFilename = reader.readLine();
            System.out.println("Received filename from server: " + receivedFilename);

            // Send a confirmation message to the server
            PrintWriter writer = new PrintWriter(out, true);
            writer.println("Received filename");

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

