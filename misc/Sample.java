import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// https://github.com/xerial/sqlite-jdbc#download

// https://www.baeldung.com/java-serialization-approaches

public class Sample
{
    public static void main(String[] args)
    {
        ArrayList<Result> results = new ArrayList<Result>();

        // NOTE: Connection and Statement are AutoCloseable.
        //       Don't forget to close them both in order to avoid leaks.
        try
        (
            // create a database connection
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
        )
        {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");

            ResultSet rs = statement.executeQuery("select id, name from person");
            while(rs.next())
            {
                Result result = new Result();

                // read the result set
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));

                results.add(result);
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }

        for(Result result : results) {
            result.println();
        }
    }
}

class Result 
{
    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    private int _id;
    public int getId() { return _id; }
    public void setId(int id) { _id = id; }   

    public void println() {
        System.out.println("name = " + getName());
        System.out.println("id = " + getId());
    }

    public String createTableStatement() {
        return "CREATE TABLE {%0} ({%1} {%2}, {%3} {%4})";
    }

    public String dropTableIfExistsStatement() {
        return "DROP TABLE IF EXITS {%0}";
    }

    public String insertStatement() {
        return "INSERT INTO {%0} VALUES({%1}, '{%2}')";
    }

    public String updateStatement() {
        return "UPDATE {%0} SET name = '{%2}' WHERE id = {%1}";
    }

    public String deleteStatement() {
        return "DELETE FROM {%0} WHERE id = {%1}";
    }
}
