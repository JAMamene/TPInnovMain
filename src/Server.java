import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    private int port;
    private RemoteList remoteList;

    public Server(int port) {
        this.port = port;
        remoteList = new RemoteList();
    }

    public void start() {
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server started");
            while (true) {
                Socket connectionSocket = socket.accept();
                new Thread() {
                    public void run() {
                        try {
                            System.out.println(connectionSocket.getInetAddress() + " connected");
                            ObjectOutputStream output = new ObjectOutputStream(connectionSocket.getOutputStream());
                            ObjectInputStream input = new ObjectInputStream(connectionSocket.getInputStream());
                            while (true) {
                                Request request = (Request) input.readObject();
                                output.writeObject(new RequestAnalyzer(request).resolveRequest(remoteList));
                                System.out.println("request from " + connectionSocket.getInetAddress() + " processed");
                            }
                        } catch (SocketException se) {
                            System.out.println(connectionSocket.getInetAddress() + " disconnected");
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(5555).start();
    }
}
