import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
/**
 * Created by Juan Fernandez on 10/25/2016.
 */
public class MovieDBTable extends AbstractTableModel {

    private static final int TABLE_MOVIE_ID_COL = 0;
    private static final int TABLE_MOVIE_TITLE_COL = 1;
    private static final int TABLE_PREMIERE_COL = 2;
    private static final int TABLE_IDGENRE_COL = 3;

    /**
     * A JTable class to the table
     *
     */

    private String[] columnNames = {"id", "title", "premiere", "id_genre"};
    private ArrayList<MovieTableInDB> movieTableInDB;

    public MovieDBTable(ArrayList<MovieTableInDB> themovieDB){
        movieTableInDB = themovieDB;
    }

    @Override
    public int getRowCount() {
        return movieTableInDB.size();
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

        MovieTableInDB tempMoTaDB = movieTableInDB.get(rowIndex);

        switch (columnIndex) {
            case TABLE_MOVIE_ID_COL:
                return tempMoTaDB.getIdMovieT();
            case TABLE_MOVIE_TITLE_COL:
                return tempMoTaDB.getTitle();
            case TABLE_PREMIERE_COL:
                return tempMoTaDB.getTPremiere();
            case TABLE_IDGENRE_COL:
                return tempMoTaDB.getId_genre();
            default:
            return tempMoTaDB.getIdMovieT();
        }
    }
}
