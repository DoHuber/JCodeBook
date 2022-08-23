import java.util.Arrays;

public class TrivialByteSource implements RandomByteSource {

    @Override
    public byte[] getBytes(int n) {

        byte[] result = new byte[n];
        Arrays.fill(result, (byte) 13);
        return result;

    }

    @Override
    public byte[] getBytesInRange(int n, byte min, byte max) {
        if (min <= 13 && 13 <= max) {

            return getBytes(n);

        } else {

            byte[] result = new byte[n];
            Arrays.fill(result, min);
            return result;

        }
    }

}
