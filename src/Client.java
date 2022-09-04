import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            InetAddress inetadress = InetAddress.getByName("localhost");
            // establishing the connection 
            Socket socket = new Socket(inetadress, 5056);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            // In the following loop, the client and client handle exchange data.
            while (true)
            {
                System.out.println(dataInputStream.readUTF());
                String tosend = scanner.nextLine();
                dataOutputStream.writeUTF(tosend);
                // Exiting from a while loop should be done when a client gives an exit message.
                if (tosend.equals("Exit"))
                {
                    System.out.println("Connection closing... : " + socket);
                    socket.close();
                    System.out.println("Closed");
                    break;
                }
                // printing date, time or qoute as requested by client
                String receivedString = dataInputStream.readUTF();
                System.out.println(receivedString);
            }
            scanner.close();
            dataInputStream.close();
            dataOutputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}