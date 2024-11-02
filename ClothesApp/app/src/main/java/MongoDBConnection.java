import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private MongoClient mongoClient;
    private MongoDatabase database;

    // Constructor to initialize the connection
    public MongoDBConnection(String username, String password, String clusterName, String databaseName) {
        String uri = "mongodb+srv://" + username + ":" + password + "@" + clusterName + ".mongodb.net/?retryWrites=true&w=majority";
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        this.mongoClient = MongoClients.create(settings);
        this.database = mongoClient.getDatabase(databaseName);
    }

    // Get the database instance
    public MongoDatabase getDatabase() {
        return this.database;
    }

    // Close the connection
    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public static void main(String[] args) {
        // Replace these with your actual credentials and database name
        String username = "root";
        String password = "123456a@";
        String clusterName = "SWaro";
        String databaseName = "SmartWardrobeDB";

        // Create a MongoDBConnection instance
        MongoDBConnection mongoDBConnection = new MongoDBConnection(username, password, clusterName, databaseName);

        // Get the database and perform operations
        MongoDatabase database = mongoDBConnection.getDatabase();
        System.out.println("Connected to database: " + database.getName());

        // Close the connection after use
        mongoDBConnection.closeConnection();
    }
}
