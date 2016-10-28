import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Juan Fernandez on 10/24/2016.
 */
public class ApplicationDatabase extends JFrame {

    /**
     * This variable blocked if you're in wrong table
     */
    //0, no table. 1, Movies table. 2, Actors table
    static int databaseSelected = 0;

    private JPanel contentPane;
    private JTextField firstNameTextField1;
    private JTextField lastNameTextField2;
    private JTextField lastNameTextField3;
    private JTextField MovieTitleTextField;
    private JButton btnSearch1;
    private JButton btnSearch2;
    private JButton btnSearch3;
    private JButton btnSearchMovie;
    private JButton btnShowMovieTable;
    private JButton btnShowActorsTable;
    private JScrollPane scrollPane;
    private JTable table;
    private AskConnection askConnection;


    /**
     * The Main method
     * @param args
     */

    public static void main(String[] args) {
        new ApplicationDatabase().setVisible(true);
    }

    /**
     * Constructor to the swing class
     */
    public ApplicationDatabase() {

        /**
         * The messenger if the sql-question is wrong
         */
        try {
            askConnection = new AskConnection();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
        /**
         * The layout
         */
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Lead Actor Search App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, (int) screen.getWidth(), 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20,20,20,20));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);

        /**
         * A JButton and a JTextFied
         */

        JLabel lblEnterFirstName1 = new JLabel("Enter the first name");
        panel.add(lblEnterFirstName1);
        firstNameTextField1 = new JTextField();
        panel.add(firstNameTextField1);
        firstNameTextField1.setColumns(10);
        btnSearch1 = new JButton("Search");
        btnSearch1.addActionListener(new ActionListener() {

            /**
             * A actionperformed method from to the sql-question, this search the first name from an actor.
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    String fname = firstNameTextField1.getText();
                    ArrayList<Actor> actors = null;
                    if (fname != null && fname.trim().length() > 0) {
                        actors = askConnection.searchActorsfname(fname);
                    } else {
                        actors = askConnection.getAllactors();
                    }

                    /**
                     * add the JTable
                     */
                    ActorTableModel model = new ActorTableModel(actors);
                    table.setModel(model);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(ApplicationDatabase.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
                databaseSelected = 0;
            }
        });
        panel.add(btnSearch1);


        JLabel lblEnterFirstName2 = new JLabel("Enter the last name");
        panel.add(lblEnterFirstName2);
        lastNameTextField2 = new JTextField();
        panel.add(lastNameTextField2);
        lastNameTextField2.setColumns(10);
        btnSearch2 = new JButton("Search");
        btnSearch2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String lname = lastNameTextField2.getText();

                    ArrayList<Actor> actors = null;
                    if (lname != null && lname.trim().length() > 0) {
                        actors = askConnection.searchActorslname(lname);
                    } else {
                        actors = askConnection.getAllactors();
                    }
                    ActorTableModel model = new ActorTableModel(actors);
                    table.setModel(model);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(ApplicationDatabase.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
                databaseSelected = 0;
            }
        });
        panel.add(btnSearch2);



        JLabel lblEnterFirstName3 = new JLabel("Enter the movie name");
        panel.add(lblEnterFirstName3);
        lastNameTextField3= new JTextField();
        panel.add(lastNameTextField3);
        lastNameTextField3.setColumns(10);
        btnSearch3 = new JButton("Search");
        btnSearch3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String title = lastNameTextField3.getText();

                    ArrayList<Actor> actors = null;

                    if (title != null && title.trim().length() > 0) {
                        actors = askConnection.searchActorsmovie(title);
                    } else {
                        actors = askConnection.getAllactors();
                    }

                    ActorTableModel model = new ActorTableModel(actors);

                    table.setModel(model);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(ApplicationDatabase.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);

                }
                databaseSelected = 0;
            }
        });
        panel.add(btnSearch3);



        JLabel lblEnterMovieTitler = new JLabel("Enter the movie name");
        panel.add(lblEnterMovieTitler);
        MovieTitleTextField = new JTextField();
        panel.add(MovieTitleTextField);
        MovieTitleTextField.setColumns(10);
        btnSearchMovie = new JButton("Search");
        btnSearchMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String title = MovieTitleTextField.getText();
                    ArrayList<Movie> movie = null;
                    if (title != null && title.trim().length() > 0) {
                        movie = askConnection.searchMovieTitle(title);
                    } else {
                        movie = askConnection.getAllMovies();
                    }
                    MovieTableModel model = new MovieTableModel(movie);
                    table.setModel(model);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(ApplicationDatabase.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
                databaseSelected = 0;
            }
        });

        panel.add(btnSearchMovie);




        btnShowMovieTable = new JButton("Show Movies Table");
        btnShowMovieTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    ArrayList<MovieTableInDB> movieTableInDB = null;
                        movieTableInDB = askConnection.getAllMovieTable();
                    MovieDBTable model = new MovieDBTable(movieTableInDB);

                    table.setModel(model);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(ApplicationDatabase.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
                databaseSelected = 1;
            }
        });

        panel.add(btnShowMovieTable);


        btnShowActorsTable = new JButton("Show Actors Table");
        btnShowActorsTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    ArrayList<ActorTableInDB> actorTableInDB = null;
                    actorTableInDB = askConnection.getAllActorsTable();
                    ActorsDBTable model = new ActorsDBTable(actorTableInDB);

                    table.setModel(model);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(ApplicationDatabase.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);

                }
                databaseSelected = 2;
            }
        });

        panel.add(btnShowActorsTable);

        JButton create = new JButton("Create");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");



        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (databaseSelected > 0) {
                        String query = null;
                        if (databaseSelected == 1) {
                            JTextField titleField = new JTextField(5);
                            JTextField dateField = new JTextField(5);
                            JTextField id_genreField = new JTextField(5);
                            Object[] options = {"Title of movie", titleField, "Date (YYYY-MM-DD)", dateField, "id_genre", id_genreField};
                            int result = JOptionPane.showConfirmDialog(panel, options, "Insert", JOptionPane.OK_CANCEL_OPTION);
                            String title = titleField.getText();
                            String date = dateField.getText();
                            int id_genre = Integer.parseInt(id_genreField.getText());
                            if (result == JOptionPane.OK_OPTION) {
                                askConnection.insertMovie("INSERT INTO movies (title, premiere, id_Genre) VALUES (?,?,?)", title, date, id_genre);
                            }
                        } else {
                            JTextField fnameField = new JTextField(5);
                            JTextField lnameField = new JTextField(5);
                            JTextField birthdateField = new JTextField(5);
                            JTextField id_movieField = new JTextField(5);
                            Object[] options = {"fname", fnameField, "lname", lnameField, "Birthdate (YYYY-MM-DD)", birthdateField, "id_movie", id_movieField};
                            int result = JOptionPane.showConfirmDialog(panel, options, "Insert", JOptionPane.OK_CANCEL_OPTION);

                            String fname = fnameField.getText();
                            String lname = lnameField.getText();
                            String birthdate = birthdateField.getText();
                            int id_movie = Integer.parseInt(id_movieField.getText());

                            if (result == JOptionPane.OK_OPTION) {
                                askConnection.insertActor("INSERT INTO lead_actors (fname, lname, birthdate, id_movie) VALUES (?,?,?,?)", fname, lname, birthdate, id_movie);
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(panel, "You must selected a table first");
                    }
                }
                catch(Exception exc){
                    System.out.println(exc);
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (databaseSelected > 0) {
                        String id = JOptionPane.showInputDialog(panel, "What row(ID) do you want to update?");
                        if (id == null) {
                            return;
                        }
                        else if (databaseSelected == 1) {

                            JTextField titleField = new JTextField(5);
                            JTextField dateField = new JTextField(5);
                            JTextField id_genreField = new JTextField(5);
                            Object[] options = {"Title of movie", titleField, "Date (YYYY-MM-DD)", dateField, "id_genre", id_genreField};
                            int result = JOptionPane.showConfirmDialog(panel, options, "Insert", JOptionPane.OK_CANCEL_OPTION);
                            String title = titleField.getText();
                            String date = dateField.getText();
                            int id_movie = Integer.parseInt(id_genreField.getText());
                            if (result == JOptionPane.OK_OPTION) {
                                askConnection.updateTable("UPDATE movies SET title=?, premiere=?, id_genre=? WHERE id=?", title, date, id_movie, Integer.parseInt(id));
                            }

                        } else {
                            JTextField fnameField = new JTextField(5);
                            JTextField lnameField = new JTextField(5);
                            JTextField birthdateField = new JTextField(5);
                            JTextField id_movieField = new JTextField(5);
                            Object[] options = {"fname", fnameField, "lname", lnameField, "Birthdate (YYYY-MM-DD)", birthdateField, "id_movie", id_movieField};
                            int result = JOptionPane.showConfirmDialog(panel, options, "Insert", JOptionPane.OK_CANCEL_OPTION);
                            String fname = fnameField.getText();
                            String lname = lnameField.getText();
                            String birthdate = birthdateField.getText();
                            int id_movie = Integer.parseInt(id_movieField.getText());
                            if (result == JOptionPane.OK_OPTION) {
                                askConnection.updateTable("UPDATE lead_actors SET fname=?, lname=?, birthdate=?, id_movie=? WHERE id=?", fname, lname, birthdate, id_movie, Integer.parseInt(id));
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "You must selected a table first");
                    }
                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }

        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(databaseSelected > 0){
                    String tableName = null;
                    if(databaseSelected == 1){
                        tableName = "movies";
                    }else{
                        tableName = "lead_actors";
                    }
                    String id = JOptionPane.showInputDialog(panel, "What row(ID) do you want to remove?");
                    if (id == null) {
                        return;
                    }
                    askConnection.removeTable("DELETE FROM " + tableName + " WHERE ID=?", Integer.parseInt(id));
                }else{
                    JOptionPane.showMessageDialog(panel, "You must selected a table first");}
            }
        });

        /**
         * add all swing
         */


        panel.add(create);
        panel.add(update);
        panel.add(delete);
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        scrollPane.setViewportView(table);


    }
}
