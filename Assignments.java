package alarMe2;

import java.util.ArrayList;
import com.steadystate.css.parser.ParseException;

public class Assignments extends LoginProcess{
	ArrayList<String> assignments;

    //konstruktør
    public Assignments(ArrayList<String> assignments) throws InterruptedException, ParseException, java.text.ParseException {
        this.assignments = assignments;
    }

    //hent assignments
    public ArrayList<String> getAssignments() {
        return assignments;
    }

}
