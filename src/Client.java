import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Request request = new QuickRequestCreator().create();
                if (request == null) {
                    System.out.println("Goodbye, closing client");
                    break;
                }
                output.writeObject(request);
                Answer answer = (Answer) input.readObject();
                new QuickAnswerHandler().handle(answer);
            }
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client("localhost",5555).start();
    }
}
