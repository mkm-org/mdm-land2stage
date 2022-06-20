package l2s;

/**
 * L2s
 */
public class App {
    public static void main( String[] args ) {
        // System.out.println( "Hello World!" );
        Service service = Util.getDefaultService();
        service.processCustomers();
    }
}
