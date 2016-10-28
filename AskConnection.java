import java.sql.*;
import java.sql.Date;
import java.util.*;
/**
 * Created by Juan Fernandez on 10/23/2016.
 */
public class AskConnection {

    private static Connection conn;

     /**
      * The connection to the database
      */
    public AskConnection() throws Exception {

        String URL = "jdbc:mysql://localhost/movie_list";
        String USER = "root";
        String PASSWORD = "";

        conn = DriverManager.getConnection(URL, USER, PASSWORD);

        System.out.println("Database connection successful");
    }


     /**
      *The Question to the database from a statement
      */
    //tar tables från vald databas
    public ArrayList<MovieTableInDB> getAllMovieTable() throws Exception {
        ArrayList<MovieTableInDB> list = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM movies");
            while (rs.next()) {
                MovieTableInDB tempMoTaDB= convertRowToMovieTable(rs);
                list.add(tempMoTaDB);
            }
            return list;
        } finally {
            close(stmt, rs);
        }
    }


     /**
      * the return from the constructor class
      *
      */

    private MovieTableInDB convertRowToMovieTable(ResultSet rs) throws SQLException {

        int idMovie = rs.getInt("id");
        String title = rs.getString("title");
        Date tPremiere = rs.getDate("premiere");
        int id_genre = rs.getInt("id_genre");

        MovieTableInDB tempMoTaDB = new MovieTableInDB(idMovie, title, tPremiere, id_genre);

        return tempMoTaDB;
    }


    public ArrayList<ActorTableInDB> getAllActorsTable() throws Exception {
        ArrayList<ActorTableInDB> list = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM lead_actors");

            while (rs.next()) {
                ActorTableInDB tempActorsTBDB= convertRowToActorsTable(rs);
                list.add(tempActorsTBDB);
            }
            return list;
        } finally {
            close(stmt, rs);
        }
    }


    private ActorTableInDB convertRowToActorsTable(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String fname = rs.getString("fname");
        String lname = rs.getString("lname");
        Date birthdate = rs.getDate("birthdate");
        int id_movie = rs.getInt("id_movie");

        ActorTableInDB tempActorsTBDB = new ActorTableInDB(id, fname, lname, birthdate, id_movie);

        return tempActorsTBDB;
    }



    public ArrayList<Actor> getAllactors() throws Exception {
        ArrayList<Actor> list = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM lead_actors INNER JOIN movies ON lead_actors.id_movie=movies.id");

            while (rs.next()) {
                Actor tempActor = convertRowToActor(rs);
                list.add(tempActor);
            }
            return list;
        } finally {
            close(stmt, rs);
        }
    }

    public ArrayList<Actor> searchActorsfname(String fname) throws Exception {
        ArrayList<Actor> list = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            fname += "%";
            stmt = conn.prepareStatement("SELECT * FROM lead_actors INNER JOIN movies ON lead_actors.id_movie=movies.id WHERE fname LIKE ?");

            stmt.setString(1, fname);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Actor tempActor = convertRowToActor(rs);
                list.add(tempActor);
            }

            return list;

        }
        finally {
            close(stmt, rs);
        }

    }

    public ArrayList<Actor> searchActorslname(String lname) throws Exception {
        ArrayList<Actor> list = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            lname += "%";
            stmt = conn.prepareStatement("SELECT * FROM lead_actors INNER JOIN movies ON lead_actors.id_movie=movies.id WHERE lname LIKE ?");

            stmt.setString(1, lname);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Actor tempActor = convertRowToActor(rs);
                list.add(tempActor);
            }

            return list;

        }
        finally {
            close(stmt, rs);
        }

    }

    public ArrayList<Actor> searchActorsmovie(String title) throws Exception {
        ArrayList<Actor> list = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            title += "%";
            stmt = conn.prepareStatement("SELECT * FROM lead_actors INNER JOIN movies ON lead_actors.id_movie=movies.id WHERE title LIKE ?");

            stmt.setString(1, title);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Actor tempActor = convertRowToActor(rs);
                list.add(tempActor);
            }

            return list;

        }
        finally {
            close(stmt, rs);
        }

    }


    private Actor convertRowToActor(ResultSet rs) throws SQLException {

        int id = rs.getInt("lead_actors.id");
        String fname = rs.getString("fname");
        String lname = rs.getString("lname");
        Date birthdate = rs.getDate("birthdate");
        String id_movie = rs.getString("title");

        Actor tempActor = new Actor(id, fname, lname, birthdate, id_movie);

        return tempActor;
    }



    public ArrayList<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> list = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM movies INNER JOIN genre ON movies.id_genre=genre.id JOIN lead_actors ON movies.id=lead_actors.id_movie ORDER BY movies.id");

            while (rs.next()) {
                Movie tempMovie = convertRowToMovie(rs);
                list.add(tempMovie);
            }
            return list;
        } finally {
            close(stmt, rs);
        }
    }


    public ArrayList<Movie> searchMovieTitle(String title) throws Exception {
        ArrayList<Movie> list = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            title += "%";
            stmt = conn.prepareStatement("SELECT * FROM movies INNER JOIN genre ON movies.id_genre=genre.id JOIN lead_actors ON movies.id=lead_actors.id_movie WHERE title LIKE ? ORDER BY movies.id");

            stmt.setString(1, title);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Movie tempMovie = convertRowToMovie(rs);
                list.add(tempMovie);
            }

            return list;

        }
        finally {
            close(stmt, rs);
        }

    }


    private Movie convertRowToMovie(ResultSet rs) throws SQLException {

        int mid = rs.getInt("movies.id");
        String mname = rs.getString("title");
        Date premiere = rs.getDate("premiere");
        String genre = rs.getString("genre");
        String lname = rs.getString("lname");
        String fname = rs.getString("fname");

        Movie tempMovie = new Movie(mid, mname, premiere, genre, fname + " " + lname);

        return tempMovie;
    }

    public void insertMovie(String query, String title, String premiere, int id_genre){
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, premiere);
            stmt.setInt(3, id_genre);
            stmt.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insertActor(String query, String fname, String lname, String birthdate, int id_movie){
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setString(3, birthdate);
            stmt.setInt(4, id_movie);
            stmt.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTable(String query, String fname, String lname, String birthdate, int id_movie, int id){

        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setString(3, birthdate);
            stmt.setInt(4, id_movie);
            stmt.setInt(5, id);
            stmt.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateTable(String query, String title, String premiere, int id_genre, int id) {

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, premiere);
            stmt.setInt(3, id_genre);
            stmt.setInt(4, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTable(String query, int id){
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }



    }


    /**
     * return if it's not null from Statment and ResultSet
     * @param conn
     * @param stmt
     * @param rs
     * @throws SQLException
     */


    private static void close(Connection conn, Statement stmt, ResultSet rs)
            throws SQLException {

        if (rs != null) {
            rs.close();
        }

        if (stmt != null) {

        }

        if (conn != null) {
            conn.close();
        }
    }

    private void close(Statement stmt, ResultSet rs) throws SQLException {
        close(null, stmt, rs);
    }


}





