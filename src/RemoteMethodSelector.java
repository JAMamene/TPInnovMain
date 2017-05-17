import javax.management.RuntimeErrorException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RemoteMethodSelector {

    public boolean select(RemoteListInterface remote) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type 1 to add idea, 2 to list, 3 to see who's interested, 4 to participate and anything to quit");
        Answer a;
        try {
            int value = sc.nextInt();
            sc.nextLine();
            switch (value) {
                case 1:
                    System.out.println("What's your name?");
                    String name = sc.nextLine();
                    System.out.println("What's your email?");
                    String email = sc.nextLine();
                    System.out.println("What's your idea?");
                    String idea = sc.nextLine();
                    System.out.println("Could you elaborate on that?");
                    String desc = sc.nextLine();
                    System.out.println("What technologies are you using (separate with comas)?");
                    List<String> technologies = Arrays.asList(sc.nextLine().split(","));
                    a = remote.add(new Idea(name, email, idea, desc, technologies));
                    if (!a.getStatus().equals(Answer.NORMAL_STATUS)) throw new RuntimeException(a.getStatus());
                    System.out.println("You added your idea");
                    break;
                case 2:
                    a = remote.list();
                    if (!a.getStatus().equals(Answer.NORMAL_STATUS)) throw new RuntimeException(a.getStatus());
                    System.out.println(a.getAnswer());
                    break;
                case 3:
                    System.out.println("What idea do you want to see (type the id)?");
                    a = remote.seeInterested(sc.nextInt());
                    if (!a.getStatus().equals(Answer.NORMAL_STATUS)) throw new RuntimeException(a.getStatus());
                    System.out.println(a.getAnswer());
                    break;
                case 4:
                    System.out.println("What idea do you want to participate to?");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("What's your email");
                    a = remote.participate(new Participation(id, sc.nextLine()));
                    if (!a.getStatus().equals(Answer.NORMAL_STATUS)) throw new RuntimeException(a.getStatus());
                    System.out.println("You participated to " + id);
                    break;
            }
        } catch (InputMismatchException m) {
            return false;
        }
        return true;
    }
}
