import javax.swing.table.*;
import java.util.ArrayList;
/**
 * Created by Juan Fernandez on 10/24/2016.
 */
public class ActorTableModel extends AbstractTableModel {

    private static final int ID_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int BIRTHDATE_COL = 3;
    private static final int ID_MOVIE_COL = 4;

    private String[] columnNames = {"ID", "First Name", "Last Name", "Birthdate", "Movie"};
    private ArrayList<Actor> actors;

    public ActorTableModel(ArrayList<Actor> theActors) {
        actors = theActors;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return actors.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Actor tempActor = actors.get(rowIndex);

        switch (columnIndex) {
            case ID_COL:
                return tempActor.getId();
            case FIRST_NAME_COL:
                return tempActor.getFname();
            case LAST_NAME_COL:
                return tempActor.getLname();
            case BIRTHDATE_COL:
                return tempActor.getBirthdate();
            case ID_MOVIE_COL:
                return tempActor.getId_movie();
            default:
                return tempActor.getId();
        }


    }
        /*@Override
        public Class getColumnClass(int c){
            return getValueAt(0, c).getClass();

    }*/
}
