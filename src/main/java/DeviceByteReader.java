import java.io.File;
import java.io.FileInputStream;

public class DeviceByteReader implements RandomByteSource{

    private final File target;

    public DeviceByteReader(String filepath) {
        target = new File(filepath);
    }

    @Override
    public byte[] getBytes(int n) {

        try (FileInputStream fis = new FileInputStream(target)) {

            byte[] result = new byte[n];
            int bytesRead = fis.read(result);
            assert bytesRead == n;
            return result;

        } catch (Exception e) {
            System.out.println("Exception! " + e.getLocalizedMessage());
            throw new RuntimeException(e.getLocalizedMessage());
        }

    }

    @Override
    public byte[] getBytesInRange(int n, byte min, byte max) {
        // Use rejection sampling to get true random bytes
        int bytesCollected = 0;
        byte[] result = new byte[n];
        while (bytesCollected < n) {

            byte[] freshBytes = getBytes(n);
            for (byte b : freshBytes) {

                if (b >= min && b <= max) {

                    result[bytesCollected] = b;
                    bytesCollected++;

                }

                if (bytesCollected == n) {
                    break;
                }
            }
        }

        return result;

    }
}
