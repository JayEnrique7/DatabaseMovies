import javax.swing.table.*;
import java.util.ArrayList;
/**
 * Created by Juan Fernandez on 10/25/2016.
 */
public class MovieTableModel extends AbstractTableModel {


    private static final int MOVIE_ID_COL = 0;
    private static final int MOVIE_TITLE_COL = 1;
    private static final int PREMIERE_COL = 2;
    private static final int GENRE_COL = 3;
    private static final int NAME_OF_ACTOR_COL = 4;
    /**
     * A JTable class to the table
     *
     */

    private String[] columnNames = {"ID", "Movie Title", "Premiere", "Genre", "Lead Actor Name"};
    private ArrayList<Movie> movies;

    public MovieTableModel(ArrayList<Movie> themovies) {
        movies = themovies;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Movie tempMovie = movies.get(rowIndex);

        switch (columnIndex) {
            case MOVIE_ID_COL:
                return tempMovie.getMid();
            case MOVIE_TITLE_COL:
                return tempMovie.getMname();
            case PREMIERE_COL:
                return tempMovie.getPremiere();
            case GENRE_COL:
                return tempMovie.getGenre();
            case NAME_OF_ACTOR_COL:
                return tempMovie.getNames();
            default:
                return tempMovie.getMid();
        }


    }


}
