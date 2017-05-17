import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;

public class Client {


    public void start() {
        try {
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";
            Remote r = Naming.lookup(url);
            System.out.println(r.getClass());
            RemoteListInterface remote = (RemoteListInterface) r;
            RemoteMethodSelector rms = new RemoteMethodSelector();
            while (rms.select(remote)) {
                //mamene
            }
        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }
}
