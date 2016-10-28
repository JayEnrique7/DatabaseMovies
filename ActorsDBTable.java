import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
/**
 * Created by Juan Fernandez on 10/25/2016.
 */
public class ActorsDBTable extends AbstractTableModel {

    //används senare vid knapparna

    private static final int TABLE_ACTOR_ID_COL = 0;
    private static final int TABLE_ACTOR_FNAME_COL = 1;
    private static final int TABLE_ACTOR_LNAME_COL = 2;
    private static final int TABLE_BIRTHDATE_COL = 3;
    private static final int TABLE_IDMOVIE_COL = 4;


    private String[] columnNames = {"id", "fname", "lname", "birthdate", "id_movie"};
    private ArrayList<ActorTableInDB> actorTableInDB;

    public ActorsDBTable(ArrayList<ActorTableInDB> theactorsDB){
        actorTableInDB = theactorsDB;
    }

    @Override
    public int getRowCount() {
        return actorTableInDB.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        ActorTableInDB tempActorsTBDB = actorTableInDB.get(rowIndex);

        switch (columnIndex) {
            case TABLE_ACTOR_ID_COL:
                return tempActorsTBDB.getId();
            case TABLE_ACTOR_FNAME_COL:
                return tempActorsTBDB.getFname();
            case TABLE_ACTOR_LNAME_COL:
                return tempActorsTBDB.getLname();
            case TABLE_BIRTHDATE_COL:
                return tempActorsTBDB.getBirthdate();
            case TABLE_IDMOVIE_COL:
                return tempActorsTBDB.getId_movie();
            default:
                return tempActorsTBDB.getId();
        }
    }


}
