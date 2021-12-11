import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to take a file of text, count each instance of every word, and
 * generate an html page displaying each word and number of instances
 *
 * @author Robert Frenken
 *
 */
public final class Project1 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Project1() {
        // no code needed here
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            // use to lower case to group both capitalized and non capitalized
            return o1.toLowerCase().compareTo(o2.toLowerCase());
        }
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *            the {@code Set} to be replaced
     * @replaces strSet
     * @ensures strSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violations of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!strSet.contains(c)) {
                strSet.add(c);
            }
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        char c = text.charAt(position);
        StringBuilder string = new StringBuilder();
        if (!separators.contains(c)) {
            for (int i = position; i < text.length(); i++) {
                if (!separators.contains(text.charAt(i))) {
                    string.append(text.charAt(i));
                } else {
                    i = i + text.length(); // if not in if statement, end for loop
                }
            }
        } else {
            for (int i = position; i < text.length(); i++) {
                if (separators.contains(text.charAt(i))) {
                    string.append(text.charAt(i));
                } else {
                    i = i + text.length(); // if not in if statement, end for loop
                }
            }
        }
        String str = string.toString();
        return str;
    }

    /**
     * Outputs the main page index.html. Expected elements from this method:
     *
     * <html> <head> <title> title of the page </title> </head> <body>
     * <h2>title</h2>
     * <hr>
     * <table>
     * <tbody>
     * <tr>
     * <td>Words</td>
     * <td>Counts</td>
     * </tr>
     * </tbody>
     * </table>
     * </hr>
     * </body> </html>
     *
     * @param map
     *            the map of terms and their occurrences
     * @param out
     *            the output stream
     * @param title
     *            the string of the file name
     * @param list
     *            the queue of unique words
     * @updates out.content
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML tags]
     */
    private static void outputhtml(Map<String, Integer> map, SimpleWriter out,
            String title, Queue<String> list) {
        assert out.isOpen() : "Violation of: out.is_open";

        out.print("<html>\r\n" + "<head>\r\n" + "<title> " + title
                + "</title>\r\n" + "\r\n" + "</head>\r\n" + "\r\n");

        out.print(
                "<body>\r\n" + "<h2>" + title + "</h2>\r\n" + "<hr>" + "\r\n");

        out.print("<table border= \"1\"> \r\n");
        out.print("<tbody>\r\n");

        out.print("<tr>\r\n");
        out.print("<th>" + "Words" + "</th>\r\n");
        out.print("<th>" + "Counts" + "</th>\r\n");
        out.print("</tr>\r\n");
        // prints the list for each word, with an anchor tag to their page
        int counter = list.length();
        for (int i = 0; i < counter; i++) {
            String word = list.dequeue();
            out.print("<tr>\r\n");
            // word here
            out.print("<td>" + word + "</td>\r\n");
            // counter here
            out.print("<td>" + map.value(word) + "</td>\r\n");
            out.print("</tr>\r\n");
        }

        out.print("</tbody>\r\n" + "</table>\r\n" + "</body>\r\n" + "</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter file that will be used to obtain the words: ");
        String textFile = in.nextLine();
        SimpleReader inFile = new SimpleReader1L(textFile);

        out.print("Enter the name of a of the html page to write to: ");
        String htmlpage = in.nextLine();
        SimpleWriter outputhtml = new SimpleWriter1L(htmlpage);

        Queue<String> list = new Queue1L<>();
        Map<String, Integer> map = new Map1L<>();

        /*
         * Define separator characters for test
         */
        final String separatorStr = " \t, .-";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        // Read file, and compile all the words into queue
        while (!inFile.atEOS()) {
            String oneLine = inFile.nextLine();
            int i = 0;
            while (i < oneLine.length()) {
                String word = nextWordOrSeparator(oneLine, i, separatorSet);
                boolean isWord = true;
                for (int j = 0; j < word.length(); j++) {
                    char c = word.charAt(j);
                    if (separatorSet.contains(c)) {
                        isWord = false;
                    }
                }
                if (isWord) {
                    list.enqueue(word);
                }
                i += word.length();
            }
        }

        // sort the list
        Comparator<String> cs = new StringLT();
        list.sort(cs);

        int iteratorNum = list.length();

        // create temp queue to make operations easier to restore
        Queue<String> temp = list.newInstance();
        for (int i = 0; i < iteratorNum; i++) {
            String word = list.dequeue();
            temp.enqueue(word);
            list.enqueue(word);
        }

        // go through queue and have a key string and value int to map
        for (int i = 0; i < iteratorNum; i++) {
            String word = list.dequeue();
            int number = 1;
            if (map.hasKey(word)) {
                int count = map.value(word);
                map.replaceValue(word, count + 1);
            } else {
                map.add(word, number);
            }

        }

        // create a set to get rid of duplicate words in queue
        Set<String> htmlWords = new Set1L<>();
        for (String x : temp) {
            if (!htmlWords.contains(x)) {
                htmlWords.add(x);
            }
        }

        // use that set to build new queue with no duplicates
        for (String x : htmlWords) {
            list.enqueue(x);
        }
        list.sort(cs);

        String title = "Words Counted in " + textFile;
        outputhtml(map, outputhtml, title, list);

        inFile.close();
        out.close();
        in.close();
    }

}
