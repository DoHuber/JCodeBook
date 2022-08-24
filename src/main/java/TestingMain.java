import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestingMain {

    private static final RandomByteSource deviceReader = new DeviceByteReader("/dev/urandom");

    public static void main(String[] args) {

        // Test the trivial byte source
        RandomByteSource source1 = new TrivialByteSource();
        byte[] bytes1 = source1.getBytes(4);
        System.out.println("The first bytes are: " + Arrays.toString(bytes1));

        // Test its numbers capability, if the min/max is outside of thirteen, it will serve the mins
        byte[] bytes2 = source1.getBytesInRange(4, (byte) 0, (byte) 4);
        System.out.println("The second set of bytes is: " + Arrays.toString(bytes2));

        // Test the DeviceByteReader
        byte[] bytes3 = deviceReader.getBytes(8);
        System.out.println("The third set of bytes is: " + Arrays.toString(bytes3));

        // Testing bytes in range, this uses rejection sampling
        byte[] bytes4 = deviceReader.getBytesInRange(32, (byte) 0, (byte) 64);
        System.out.println("The fourth set of bytes is: " + Arrays.toString(bytes4));

        // Generate test pages and write them to disk
        generateTestPages();

    }

    // Generate some test pages using the trivial source
    private static void generateTestPages() {

        List<String> pages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            byte[] random = deviceReader.getBytesInRange(150, (byte) 0, (byte) 35);
            Page p = new Page(random);
            pages.add(p.toString());

        }

        String pagesStr = String.join("\n", pages);
        try (PrintWriter out = new PrintWriter("pages.tex")) {

            out.println(pagesStr);

        } catch (Exception e) {
            System.out.println("Exception, too bad! Msg.: " + e.getLocalizedMessage());
        }

    }
}
