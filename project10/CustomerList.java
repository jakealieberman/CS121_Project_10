/* CustomerList.java
   wrapper list to avoid deserialization warnings.
*/
import java.io.Serializable;
import java.util.ArrayList;

public class CustomerList extends ArrayList<Customer> implements Serializable {
    private static final long serialVersionUID = 1L;
}