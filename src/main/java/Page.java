import java.util.ArrayList;
import java.util.List;

public class Page {

    private final byte[] randomBytes;
    private static final String PAGE_PREFIX = "\\begin{flushleft}" 
            + "\\begin{tabular}{c c c c c @{\\hskip 2em} c c c c c @{\\hskip 2em} c c c c c}";
    private static final String PAGE_SUFFIX = "\\end{tabular}\n\\end{flushleft}\n\\newpage";

    public Page(byte[] randomBytes) {
        if (randomBytes.length < 150) {
            throw new IllegalArgumentException("Need at least 150 random bytes for one page.");
        }
        this.randomBytes = randomBytes;
    }

    private String[] byteArrayToStringArray(byte[] arr) {

        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Byte.toString(arr[i]);
        }
        return result;

    }

    @Override
    public String toString() {
        // TO LaTeX String
        int arrayPointer = 0;
        List<String> blocks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            byte[] currentBytes = new byte[15];
            for (int j = 0; j < 15; j++) {

                currentBytes[j] = randomBytes[arrayPointer];
                arrayPointer++;

            }

            String block = "\t" + String.join(" & ", byteArrayToStringArray(currentBytes)) + "\\\\"
                    + "\\U & \\U & \\U & \\U & \\U & \\U & \\U & \\U & \\U & \\U & \\U & \\U & \\U & \\U & \\U \\\\ \\\\";
            blocks.add(block);
        }

        String blocksStr = String.join("\\hline \\ \n", blocks);
        return PAGE_PREFIX + "\n" + blocksStr + "\n" + PAGE_SUFFIX;

    }
}
