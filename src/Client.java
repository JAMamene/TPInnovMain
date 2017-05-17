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
            RemoteListInterface remote = (RemoteListInterface) r;
            RemoteMethodSelector rms = new RemoteMethodSelector();
            try {
                while (rms.select(remote)) {
                    //mamene
                }
            } catch (RuntimeException re) {
                System.err.println(re.getMessage());
            }
        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Goodbye");
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }
}
