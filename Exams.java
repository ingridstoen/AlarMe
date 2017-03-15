package alarMe2;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ingridstoen on 02.03.2017.
 */

public class Exams extends LoginProcess {

    private HashMap<String, Date> exams;

    public Exams(HashMap<String, Date> exams) throws InterruptedException, ParseException {
        this.exams = exams;
    }

    public HashMap<String, Date> getExams() {
        return exams;
    }

}
