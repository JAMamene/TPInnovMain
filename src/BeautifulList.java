import java.util.ArrayList;
import java.util.Iterator;


public class BeautifulList extends ArrayList<Object> {
    @Override
    public String toString() {
        Iterator<Object> it = iterator();
        if (!it.hasNext()) {
            return "\nNothing here yet :(\n";
        }
        StringBuilder sb = new StringBuilder("\n");
        for (; ; ) {
            Object e = it.next();
            sb.append(e);
            if (!it.hasNext())
                return sb.toString();
            sb.append('\n');
        }
    }
}
