import java.util.ArrayList;

class KMPAlgo {

    long cpt;

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
            cpt++;
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
        testCasKmpAlgo(text1, "t");

        ArrayList<Character> text2 = generateText(100);
        testCasKmpAlgo(text2, "ta");
    }

    /**
     * Runs a single test case of the KMP algorithm
     * and displays the text, the pattern, and the result.
     *
     * @param text    the text to analyze
     * @param pattern the pattern to search for
     */
    void testCasKmpAlgo(ArrayList<Character> text, String pattern) {
        System.out.println("Text generé aleatoirement : ");
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
        int n = 500000;
        long t1, t2, diffT;
        String pattern = "aaaaaaaaab";

        System.out.println("----- Tests d'efficacité de l'algorithme KMP : -----\n");
        System.out.println(" -- Texte composé de 'a' répétés puis 'b' et motif \"aaaaaaaaab\"\n");

        for (int i = 1; i <= 6; i++) {

            ArrayList<Character> text = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                text.add('a');
            }
            text.add('b');

            cpt = 0;
            t1 = System.nanoTime();
            kmpAlgo(text, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1;

            System.out.println("Taille du texte : " + n);
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println("Nombre d'opérations/n (cpt/n) : " + (cpt / (double) n));
            System.out.println();

            n *= 2;
        }
        System.out.println(" -- Texte avec alphabet entier aléatoire et motif \"abf\"\n");
        n = 500000;
        pattern = "abf";
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateText(n);

            System.out.println("Taille du texte : " + n);
            cpt = 0;
            t1 = System.nanoTime();
            kmpAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1;
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println("Nombre d'opérations/n (cpt/n) : " + (cpt / (double) n));
            System.out.println();

            n = n * 2;
        }
        System.out.println("--------------------------------------------------");
    }

}

// commentaire Bien que le nombre de comparaisons puisse dépasser la taille du
// texte, il reste proportionnel à n.
// Chaque caractère du texte est comparé au plus deux fois, ce qui garantit la
// complexité linéaire de l’algorithme KMP.
// meme dans le pire des cas, on aura toujours cpt <= 2n
// exemple : text = AAAA....AAAAB, pattern = AAAAAAAAAAB, cpt = 2n - (taille du
// pattern - 2)