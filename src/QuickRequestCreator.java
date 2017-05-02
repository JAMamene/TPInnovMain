import java.util.*;

public class QuickRequestCreator {

    public Request create () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type 1 to add idea, 2 to list, 3 to see who's interested, 4 to participate and anything to quit");
        Request request;
        int value = sc.nextInt();
        sc.nextLine();
        try {
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
                    request = new Request(new Idea(name, email, idea, desc, technologies));
                    break;
                case 2:
                    request = new Request();
                    break;
                case 3:
                    System.out.println("What idea do you want to see (type the id)?");
                    request = new Request(sc.nextInt());
                    break;
                case 4:
                    System.out.println("What idea do you want to participate to?");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("What's your email");
                    request = new Request(new Participation(id, sc.nextLine()));
                    break;
                default:
                    return null;
            }
        } catch (InputMismatchException m) {
            return null;
        }
        System.out.println("Your request is being sent to the server");
        return request;
    }
}
