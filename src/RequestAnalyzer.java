import java.lang.reflect.InvocationTargetException;

public class RequestAnalyzer {

    private Request request;

    public RequestAnalyzer(Request request) {
        this.request = request;
    }

    public Answer resolveRequest(RemoteList remoteList) {
        try {
            return request.invoke(remoteList);
        } catch (InvocationTargetException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            return new Answer(Answer.BAD_STATUS,null);
        }
    }
}
