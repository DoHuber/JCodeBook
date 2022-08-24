import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static RandomByteSource randSource;

    private static String getPagesLatex(int n) {

        List<String> pages = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            byte[] randomness = randSource.getBytesInRange(150, (byte) 0, (byte) 35);
            Page p = new Page(randomness);
            pages.add(p.toString());

        }

        return String.join(System.lineSeparator(), pages);

    }

    private static String renderTemplate(String pages) {

        try {

        byte[] b = Files.readAllBytes(Paths.get(Main.class.getResource("codebook-template.txt").toURI()));
        String template = new String(b);
        return template.replace("<pageshere>", pages);

        } catch (Exception e) {
            System.out.println("Exception, too bad! Msg.: " + e.getLocalizedMessage());
        }

        return "Something has gone wrong. Consult the logs.";
    }


    // Parameters: Randomness device, Pages
    public static void main(String[] args) {

        randSource = new DeviceByteReader(args[0]);
        int nPages = Integer.parseInt(args[1]);
        String pages = getPagesLatex(nPages);
        String book = renderTemplate(pages);

        try (PrintWriter out = new PrintWriter("output/codebook.tex")) {

            out.println(book);

        } catch (Exception e) {
            System.out.println("Exception! " + e.getLocalizedMessage());
        }

        System.out.println("Finished, check output dir.");

    }
}
