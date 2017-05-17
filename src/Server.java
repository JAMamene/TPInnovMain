import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {

    private int port;
    private RemoteList remoteList;

    public Server(int port) throws RemoteException {
        this.port = port;
        remoteList = new RemoteList();
    }

    public void start() {
        try {
            System.out.println("Server started");
            RemoteList remote = new RemoteList();
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";
            System.out.println("Enregistrement de l'objet avec l'url : " + url);
            Naming.rebind(url, remote);
        } catch (UnknownHostException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws RemoteException {
        new Server(5555).start();
    }
}
