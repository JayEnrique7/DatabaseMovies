import java.sql.Date;
/**
 * Created by Juan Fernandez on 10/25/2016.
 */
public class MovieTableInDB {

    private int idMovieT;
    private String title;
    private Date tPremiere;
    private int id_genre;

    public MovieTableInDB(int idMovieT, String title, Date tPremiere, int id_genre)
    {

        super();
        this.idMovieT = idMovieT;
        this.title = title;
        this.tPremiere = tPremiere;
        this.id_genre = id_genre;

    }

    /**
     * Constructor class
     * @return
     */

    public int getIdMovieT() {
        return idMovieT;
    }

    public void setIdMovieT(int idMovieT){
        this.idMovieT = idMovieT;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Date getTPremiere(){
        return tPremiere;
    }

    public void setTPremiere(Date tPremiere){
        this.tPremiere = tPremiere;
    }

    public int getId_genre(){
        return id_genre;
    }

    public void setId_genre(int id_genre){
        this.id_genre = id_genre;
    }




}
