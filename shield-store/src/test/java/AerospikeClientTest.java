import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;
import org.junit.jupiter.api.Test;

/**
 * aerospike client test
 *
 * @author moon on 2024/3/25 by idea
 */
public class AerospikeClientTest {

    @Test
    void connection() {

        String address = "127.0.0.1";   // Aerospike address
        int port = 3000;            // Aerospike port
        String namespace = "test";      // Cluster namespace
        String set = "foo";             // Set name within namespace

        // Step 1: Client connection
        IAerospikeClient client = new AerospikeClient(address, port);

        // Step 2: Write a record
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.totalTimeout = 5000;

        Key key = new Key(namespace, set, "bar");

        Bin bin = new Bin("myBin", "Hello World!");

        try {
            client.put(writePolicy, key, bin);
            System.out.println("Successfully wrote record");
        }
        catch (AerospikeException e) {
            e.printStackTrace();
        }

        // Step 3: Read a record
        Policy readPolicy = new Policy();
        readPolicy.totalTimeout = 5000;

        try {
            Record record = client.get(readPolicy, key);
            System.out.format("Record: %s", record.bins);
        }
        catch (AerospikeException e) {
            e.printStackTrace();
        }
        finally {
            client.close();
        }
    }

}
