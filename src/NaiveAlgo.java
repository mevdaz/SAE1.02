import java.util.ArrayList;

/**
 * Implementation of the naive string-search algorithm.
 * Includes performance tests and utility methods to generate test texts.
 */

class NaiveAlgo {

    /**
     * Global counter to measure the algorithm's performance.
     */
    long cpt;

    /**
     * Main method to run tests of the naive algorithm.
     */
    void principal() {
        testNaiveAlgo();
        testNaiveAlgoEfficiency();
        testGenerateSequenceText();
    }

    /**
     * Implements the naive algorithm to find all occurrences of a pattern in a
     * text.
     * 
     * @param text    the text in which to search for the pattern.
     * @param pattern the pattern to search for.
     * @return a list of starting indices for each occurrence of the pattern in the
     *         text.
     */
    ArrayList<Integer> naiveAlgo(ArrayList<Character> text, String pattern) {
        ArrayList<Integer> tab = new ArrayList<>();
        for (int i = 0; i <= (text.size() - pattern.length()); i++) {
            cpt++; // incrémenter le compteur d'opérations
            if (text.get(i) == pattern.charAt(0)) {
                int j = 1;
                while (j < pattern.length() && text.get(i + j) == pattern.charAt(j)) {
                    j++;
                }
                if (j == pattern.length()) {
                    tab.add(i);
                }
            }
        }
        return tab;
    }

    /**
     * Test method for the naive algorithm.
     */
    void testNaiveAlgo() {
        System.out.println("Test NaiveAlgo");
        ArrayList<Character> text = generateText(100000);
        testCasNaiveAlgo(text, "toto");
        ArrayList<Character> text2 = generateText(100000);
        testCasNaiveAlgo(text2, "tata");
    }


    /**
     * Tests a specific case of the naive algorithm.
     * 
     * @param text    the text in which to search for the pattern.
     * @param pattern the pattern to search for.
     */
    void testCasNaiveAlgo(ArrayList<Character> text, String pattern) {
        System.out.println("Text : " + text);
        System.out.println("Pattern : " + pattern);
        ArrayList<Integer> tab = naiveAlgo(text, pattern);
        System.out.println("Tab : " + tab);
    }

    /**
     * Tests the efficiency of the naive algorithm.
     */
    void testNaiveAlgoEfficiency() {
        long t1, t2, diffT;
        System.out.println("----- Tests d'efficacité de l'algorithme naïf : -----");
        int size = 500000;
        String pattern = "ab";
        System.out.println(" -- Texte avec alphabet restreint {a} et motif \"ab\"\n");
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateLettreText(size, 'a');
            System.out.println("Taille du texte : " + size);
            cpt = 0; // reset le compteur
            t1 = System.nanoTime();
            naiveAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1; // en nanosecondes
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println("Nombre d'opérations/n (cpt/n) : " + (cpt / (double) size));
            System.out.println();
            size = size * 2;
        }
        System.out.println(" -- Texte avec alphabet entier aléatoire et motif \"abf\"\n");
        size = 500000;
        pattern = "abf";
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateText(size);
            System.out.println("Taille du texte : " + size);
            cpt = 0; // reset le compteur
            t1 = System.nanoTime();
            naiveAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1; // en nanosecondes
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println("Nombre d'opérations/n (cpt/n) : " + (cpt / (double) size));
            System.out.println();
            size = size * 2;
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Generates a random text with a restricted alphabet for performance tests.
     * 
     * @param size the size of the text to generate.
     * @return a list of characters representing the generated text.
     */
    ArrayList<Character> generateSequenceText(int size) {
        char[] alphabet = { 'a', 'b', 'c', 'd' };
        size = size / 5;
        ArrayList<Character> text = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            char lettre = alphabet[(int) (Math.random() * alphabet.length)];
            for (int j = 0; j < 5; j++) {
                text.add(lettre);
            }
        }
        return text;
    }

    /**
     * Test method for generateSequenceText.
     */
    void testGenerateSequenceText() {
        System.out.println("Test generateSequenceText");
        ArrayList<Character> text = generateSequenceText(100);
        System.out.println("Generated text: " + text);
    }

    /**
     * Generates a sequence of the same letter of the specified size for performance
     * tests.
     * 
     * @param size   the size of the text to generate.
     * @param lettre the letter to repeat.
     * @return a list of characters representing the generated text.
     */
    ArrayList<Character> generateLettreText(int size, char lettre) {
        ArrayList<Character> text = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            text.add(lettre);
        }
        return text;
    }

    /**
     * Test method for generateLettreText.
     */
    void testGenerateLettreText() {
        System.out.println("Test generateLettreText");
        ArrayList<Character> text = generateLettreText(50, 'x');
        System.out.println("Generated text: " + text);
    }

    /**
     * Generates a random text of the specified size.
     * 
     * @param size the size of the text to generate.
     * @return a list of characters representing the generated text.
     */
    ArrayList<Character> generateText(int size) {
        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        ArrayList<Character> text = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            text.add(alphabet[(int) (Math.random() * 26)]);
        }
        return text;
    }

    /**
     * Test method for generateText.
     */
    void testGenerateText() {
        System.out.println("Test generateText");
        ArrayList<Character> text = generateText(100);
        System.out.println("Generated text: " + text);
    }
}
