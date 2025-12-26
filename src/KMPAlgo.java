import java.util.ArrayList;

/**
 * Implementation of the Knuth-Morris-Pratt (KMP) string matching algorithm.
 * This class provides methods to search for all occurrences of a pattern
 * in a given text using the KMP algorithm, as well as test methods
 * to validate correctness and measure performance.
 */
class KMPAlgo {

    /**
     * Main method that launches all tests.
     * It runs both correctness tests and performance tests.
     */
    void principal() {
        testKmpAlgo();
        testKmpAlgoEfficiency();
    }

    /**
     * Generates a random text composed of lowercase letters.
     *
     * @param size the length of the text to generate
     * @return an ArrayList of characters representing the generated text
     */
    ArrayList<Character> generateText(int size) {
        char[] alphabet = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };

        ArrayList<Character> text = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            text.add(alphabet[(int) (Math.random() * alphabet.length)]);
        }

        return text;
    }

    /**
     * Applies the KMP algorithm to search for a pattern in a text.
     *
     * @param text    the text in which the pattern is searched
     * @param pattern the pattern to search for
     * @return a list of indices where the pattern occurs in the text
     */
    ArrayList<Integer> kmpAlgo(ArrayList<Character> text, String pattern) {

        ArrayList<Integer> result = new ArrayList<>();

        int n = text.size();
        int m = pattern.length();

        if (m == 0 || n == 0 || m > n) {
            return result;
        }

        int[] pi = buildPrefixTable(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {

            if (text.get(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == m) {
                    result.add(i - j);
                    j = pi[j - 1];
                }
            } else {
                if (j > 0) {
                    j = pi[j - 1];
                } else {
                    i++;
                }
            }
        }

        return result;
    }

    /**
     * Builds the prefix table (also called failure function or π table)
     * for the given pattern.
     * The prefix table indicates for each position the length of the
     * longest proper prefix which is also a suffix.
     *
     * @param pattern the pattern used to build the prefix table
     * @return an array representing the prefix table
     */
    int[] buildPrefixTable(String pattern) {

        int m = pattern.length();
        int[] pi = new int[m];

        int i = 1;
        int j = 0;

        while (i < m) {

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                pi[i] = j;
                i++;
            } else {
                if (j > 0) {
                    j = pi[j - 1];
                } else {
                    pi[i] = 0;
                    i++;
                }
            }
        }

        return pi;
    }

    /**
     * Tests the correctness of the KMP algorithm
     * using randomly generated texts and simple patterns.
     */
    void testKmpAlgo() {
        System.out.println("Test KMPAlgo");

        ArrayList<Character> text1 = generateText(100);
        TestCasKmpAlgo(text1, "t");

        ArrayList<Character> text2 = generateText(100);
        TestCasKmpAlgo(text2, "ta");
    }

    /**
     * Runs a single test case of the KMP algorithm
     * and displays the text, the pattern, and the result.
     *
     * @param text    the text to analyze
     * @param pattern the pattern to search for
     */
    void TestCasKmpAlgo(ArrayList<Character> text, String pattern) {
        System.out.println("Text : " + text);
        System.out.println("Pattern : " + pattern);

        ArrayList<Integer> tab = kmpAlgo(text, pattern);

        System.out.println("Occurrences : " + tab);
    }

    /**
     * Measures the efficiency of the KMP algorithm on a large input.
     * The text is composed of many identical characters followed by
     * a different one, which represents a worst-case scenario
     * for naive string matching algorithms.
     */
    void testKmpAlgoEfficiency() {

        ArrayList<Character> text = new ArrayList<>();

        // Very long text for performance measurement (e.g., 50,000 'A's followed by a
        // 'B')
        int size = 50000;
        for (int i = 0; i < size; i++) {
            text.add('A');
        }
        text.add('B');

        String pattern = "AAAAAAAAAAB";

        long start = System.nanoTime();
        kmpAlgo(text, pattern);
        long end = System.nanoTime();

        System.out.println("KMP efficiency test:");
        System.out.println("Execution time (ns): " + (end - start));
        System.out.println();
    }
}
