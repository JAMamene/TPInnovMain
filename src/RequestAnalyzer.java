import java.lang.reflect.InvocationTargetException;

public class RequestAnalyzer {

    private Request request;

    public RequestAnalyzer(Request request) {
        this.request = request;
    }

    public Answer resolveRequest(Stuff stuff) {
        try {
            return request.invoke(stuff);
        } catch (InvocationTargetException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new Answer(Answer.BAD_STATUS,null);
    }
}
