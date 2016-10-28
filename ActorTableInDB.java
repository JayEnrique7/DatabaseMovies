import java.sql.Date;
/**
 * Created by Juan Fernandez on 10/25/2016.
 */
public class ActorTableInDB {

    private int id;
    private String lname;
    private String fname;
    private Date birthdate;
    private int id_movie;

    public ActorTableInDB(int id, String fname, String lname, Date birthdate, int id_movie){

        super();
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.birthdate = birthdate;
        this.id_movie = id_movie;

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFname(){
        return fname;
    }

    public void setFname(String fname){
        this.fname = fname;
    }

    public String getLname(){
        return lname;
    }

    public void setLname(String lname){
        this.lname = lname;
    }

    public Date getBirthdate(){
        return birthdate;
    }

    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }

    public int getId_movie(){
        return id_movie;
    }

    public void setId_movie(int id_movie){
        this.id_movie = id_movie;
    }

    /**
     * A method to the sql-question in database when the question contains '%'
     * @return
     */

    @Override
    public String toString(){
        return String.format("Actor [id=%s, fname=%s, lname=%s, birthdate=%s , id_movie=%s]", id, fname, lname,
                birthdate, id_movie);


    }


}
