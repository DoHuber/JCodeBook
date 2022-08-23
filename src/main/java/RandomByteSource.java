public interface RandomByteSource {

    byte[] getBytes(int n);

    byte[] getBytesInRange(int n, byte min, byte max);

}
