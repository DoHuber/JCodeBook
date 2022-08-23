import java.util.Arrays;

public class TestingMain {
    public static void main(String[] args) {

        // Test the trivial byte source
        RandomByteSource source1 = new TrivialByteSource();
        byte[] bytes1 = source1.getBytes(4);
        System.out.println("The first bytes are: " + Arrays.toString(bytes1));

        // Test its numbers capability, if the min/max is outside of thirteen, it will serve the mins
        byte[] bytes2 = source1.getBytesInRange(4, (byte) 0, (byte) 4);
        System.out.println("The second set of bytes is: " + Arrays.toString(bytes2));

        // Test the DeviceByteReader
        RandomByteSource source2 = new DeviceByteReader("/dev/urandom");
        byte[] bytes3 = source2.getBytes(8);
        System.out.println("The third set of bytes is: " + Arrays.toString(bytes3));

        // Testing bytes in range, this uses rejection sampling
        byte[] bytes4 = source2.getBytesInRange(32, (byte) 0, (byte) 64);
        System.out.println("The fourth set of bytes is: " + Arrays.toString(bytes4));

    }
}
