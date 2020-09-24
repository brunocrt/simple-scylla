package brunocrt.simple.scylla;

import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

/**
 * Simple Application
 */
public class App {


    public App() {

    }

    public void executeQuery(String email) {

        CqlSession session = null;

        try {

            session = CqlSession.builder()
            .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
            .withLocalDatacenter("datacenter1")
            .withKeyspace("test")
            .build();
    
            ResultSet rs = session.execute(
                SimpleStatement.builder("SELECT * FROM users WHERE email=?")
                        .addPositionalValue(email)
                        .build());
            
            Row row = rs.one();

            if(row != null) {
                System.out.println("-----------------------------------------------");
                System.out.println("User: "+row.getString("email")+" - "+row.getString("firstname") +" - "+ row.getString("lastname"));
                System.out.println("-----------------------------------------------");    
            } else {
                System.out.println("User table is empty!");
            }


        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }


    }

    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Connecting to ScyllaDB...");

        String email = "u1@sample.com";
        if(args.length > 0) {
            email = args[0];
        }

        System.out.format("Querying user by email %s...\n", email);

        App app = new App();
        app.executeQuery(email);    

    }
}
