import java.lang.reflect.InvocationTargetException;

public class RequestAnalyzer {

    private Request request;

    public RequestAnalyzer(Request request) {
        this.request = request;
    }

    public Answer resolveRequest(RemoteList remoteList) {
        try {
            if (request.getParam() == null) {
                return (Answer) (Class.forName(remoteList.getClass().getName()).getMethod(request.getMethod())).invoke(remoteList);
            } else {
                return (Answer) (Class.forName(remoteList.getClass().getName()).getMethod(request.getMethod(), request.getParam().getClass()).invoke(remoteList, request.getParam()));
            }
        } catch (InvocationTargetException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            return new Answer("Problem when reflecting (Someone must have messed up the protocol (Guillaume))", null);
        }
    }
}
