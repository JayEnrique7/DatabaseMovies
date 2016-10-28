import java.sql.Date;
/**
 * Created by Juan Fernandez on 10/25/2016.
 */
public class Movie {

    private int mid;
    private String mname;
    private Date premiere;
    private String genre;
    private String names;


    public Movie(int mid, String mname, Date premiere, String genre, String names)
    {

        super();
        this.mid = mid;
        this.mname = mname;
        this.premiere = premiere;
        this.genre = genre;
        this.names = names;

    }


    public int getMid() {
        return mid;
    }

    public void setMid(int mid){
        this.mid = mid;
    }

    public String getMname(){
        return mname;
    }

    public void setMname(String mname){
        this.mname = mname;
    }

    public Date getPremiere(){
        return premiere;
    }

    public void setPremiere(Date premiere){
        this.premiere = premiere;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getNames(){
        return names;
    }

    public void setNames(String names){
        this.names = names;
    }



    @Override
    public String toString(){
        return String.format("Movie [movie.id=%s, title=%s, premiere=%s, genre=%s, lname=%s, fname=%s}", mid, mname, premiere,
                genre, names);
    }
}
