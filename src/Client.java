import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.util.Arrays;

public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";
            Remote r = Naming.lookup(url);
            System.out.println(r.getClass());
            RemoteListInterface remote = (RemoteListInterface)  r;
            remote.add(new Idea("m","a","m","e", Arrays.asList("n","e")));
            System.out.println(remote.list());
        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client("localhost",5555).start();
    }
}
