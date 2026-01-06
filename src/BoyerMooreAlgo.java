import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implementation of the Boyer-Moore string search algorithm with both bad character and good suffix rules.
 * Includes efficiency tests and utility methods for generating test texts.
 */
class BoyerMooreAlgo {

    /**
     * Global counter to measure the efficiency of the algorithms.
     */
    long cpt;

    /**
     * Main method to run Boyer-Moore algorithm tests.
     */
    void principal() {
        testBoyerMooreAlgo();
        testBoyerMooreAlgoEfficiency();
    }

    /**
     * Computes the shift to apply according to the good suffix rule (Table 2).
     * @param pattern The pattern to search for.
     * @param j The index where the mismatch occurred in the pattern.
     * @return The shift to apply (good suffix rule, D2).
     */
    int calculDecalageBonSuffixe(String pattern, int j) {
        int m = pattern.length();
        int shift = m; // Default: full shift

        // Special case: error on the last character (empty suffix)
        if (j == m - 1) {
            shift = 1;
        } else {
            int uLen = m - (j + 1);
            // Rule 1: Search for another occurrence of u preceded by a character different from pattern[j]
            for (int k = j - uLen; k >= 0; k--) {
                boolean match = true;
                for (int l = 0; l < uLen; l++) {
                    if (pattern.charAt(k + l) != pattern.charAt(j + 1 + l)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    if (k == 0 || pattern.charAt(k - 1) != pattern.charAt(j)) {
                        shift = j - k + 1;
                        break;
                    }
                }
            }
            // Rule 2: Search for the longest prefix of the pattern that is also a suffix of u
            if (shift == m && uLen > 0) {
                for (int z = uLen; z > 0; z--) {
                    boolean prefixMatch = true;
                    for (int l = 0; l < z; l++) {
                        if (pattern.charAt(l) != pattern.charAt(m - z + l)) {
                            prefixMatch = false;
                            break;
                        }
                    }
                    if (prefixMatch) {
                        shift = m - z;
                        break;
                    }
                }
            }
        }
        return shift;
    }

    /**
     * Computes the shift to apply according to the bad character rule.
     * @param pattern The pattern to search for.
     * @param c The mismatched character in the text.
     * @param posErreur The position of the mismatch in the pattern.
     * @return The shift to apply (bad character rule).
     */
    int decalage(String pattern, char c, int posErreur) {

        int result = posErreur + 1;
        for (int i = posErreur; i >= 0; i--) {
            if (pattern.charAt(i) == c) {
                result = posErreur - i;
                break;
            }
        }
        if (result < 1) {
            result = 1;
        }
        return result;
    }
    
    /**
     * Implements the Boyer-Moore algorithm to find all occurrences of a pattern in a text.
     * @param texte The text in which to search for the pattern.
     * @param pattern The pattern to search for.
     * @return A list of start indices for each occurrence of the pattern in the text.
     */
    ArrayList<Integer> boyerMooreAlgo(ArrayList<Character> texte, String pattern) {

        ArrayList<Integer> result = new ArrayList<>();
        int indPattern = 0;
        int indFinPattern = pattern.length() - 1;
        while (indFinPattern < texte.size()) {
            int j = pattern.length() - 1;
            while (j >= 0 && texte.get(indPattern + j) == pattern.charAt(j)) {
                j--;
            }
            cpt++; // increment the counter
            if (j < 0) {
                result.add(indPattern);
                indPattern += 1;
                indFinPattern = indPattern + pattern.length() - 1;
            } else {
                int d1 = decalage(pattern, texte.get(indPattern + j), j);
                int d2 = calculDecalageBonSuffixe(pattern, j);
                int decale = Math.max(d1, d2);
                indPattern += decale;
                indFinPattern = indPattern + pattern.length() - 1;
            }
        }
        return result;
    }

    /**
     * Test method for the Boyer-Moore algorithm.
     */
    void testBoyerMooreAlgo() {

        // System.out.println("\n----- Tests de l'algorithme de Boyer-Moore : -----\n");
        
        ArrayList<Character> texte1 = new ArrayList<>(Arrays.asList(
            'I','l',' ','a',' ','r','e','g','a','r','d','é',' ','d','e','d','a','n','s',',',
            ' ','e','s','t',' ','e','n','t','r','é',' ','d','e','d','a','n','s',',',
            ' ','p','u','i','s',' ','a',' ','c','a','c','h','é',' ',
            'l','a',' ','c','l','é',' ','d','e','d','a','n','s','.'
        ));
        String pattern1 = "dedans";
        ArrayList<Integer> resultAttendu1 = new ArrayList<>(Arrays.asList(
            13, 31, 59
        ));

        testCasBoyerMooreAlgo(texte1, pattern1, resultAttendu1);

        ArrayList<Character> texte2 = new ArrayList<>(Arrays.asList(
            'I','l',' ','e','s','t',' ','e','n','t','r','é',' ','d','a','n','s',' ','l','a',' ',
            'm','a','i','s','o','n',',',' ',
            'a',' ','n','e','t','t','o','y','é',' ','l','a',' ',
            'm','a','i','s','o','n',',',' ',
            'p','u','i','s',' ','a',' ','q','u','i','t','t','é',' ','l','a',' ',
            'm','a','i','s','o','n','.'
        ));
        String pattern2 = "maison";
        ArrayList<Integer> resultAttendu2 = new ArrayList<>(Arrays.asList(
            21, 42, 67
        ));

        testCasBoyerMooreAlgo(texte2, pattern2, resultAttendu2);

        ArrayList<Character> texte3 = new ArrayList<>(Arrays.asList(
            'L','e',' ','s','o','l','e','i','l',' ','b','r','i','l','l','e',' ','s','u','r',' ',
            'l','a',' ','m','e','r',' ','e','t',' ','l','e','s',' ',
            'o','i','s','e','a','u','x',' ','c','h','a','n','t','e','n','t',' ',
            'j','o','y','e','u','s','e','m','e','n','t','.'
        ));
        String pattern3 = "ordinateur";
        ArrayList<Integer> resultAttendu3 = new ArrayList<>(Arrays.asList(
        ));

        testCasBoyerMooreAlgo(texte3, pattern3, resultAttendu3);

        // System.out.println("\n--------------------------------------------------\n");

    }

    /**
     * Tests a specific case of the Boyer-Moore algorithm.
     * @param texte The text in which to search for the pattern.
     * @param pattern The pattern to search for.
     * @param resultAttendu The expected list of indices for the pattern occurrences.
     */
    void testCasBoyerMooreAlgo(ArrayList<Character> texte, String pattern, ArrayList<Integer> resultAttendu) {
        
        ArrayList<Integer> result = boyerMooreAlgo(texte, pattern);

        boolean equals = true;
        if (result.size() != resultAttendu.size()) {
            equals = false;
        } else {
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) != resultAttendu.get(i)) {
                    equals = false;
                    break;
                }
            }
        }
        if (equals) {
            System.out.println("Test passed for pattern \"" + pattern.toString() + "\".");
        } else {
            System.out.println("Test failed for pattern \"" + pattern.toString() + "\". Expected: " 
                + resultAttendu.toString() + ", Got: " + result.toString());
        }
    }

    /**
     * Generates a random text of the specified size.
     * @param size The size of the text to generate.
     * @return A list of characters representing the generated text.
     */
    ArrayList<Character> generateRandomText(int size) {
        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        ArrayList<Character> text = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            text.add(alphabet[(int) (Math.random() * alphabet.length)]);
        }
        return text;
    }

    /**
     * Generates a random text with a restricted alphabet for efficiency testing.
     * @param size The size of the text to generate.
     * @return A list of characters representing the generated text.
     */
    ArrayList<Character> generateSequenceText(int size) {
        char[] alphabet = { 'a', 'b', 'c', 'd'};
        size = size/5;
        ArrayList<Character> text = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            char lettre = alphabet[(int) (Math.random() * alphabet.length)];
            for (int j = 0; j < 5; j++){
                text.add(lettre);
            }
        }
        return text;
    }

    /**
     * Generates a sequence of the same letter of the specified size for efficiency testing.
     * @param size The size of the text to generate.
     * @param lettre The letter to repeat.
     * @return A list of characters representing the generated text.
     */
    ArrayList<Character> generateLettreText(int size, char lettre) {

        ArrayList<Character> text = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            text.add(lettre);
        }
        return text;
    }

    /**
     * Tests the efficiency of the Boyer-Moore algorithm.
     */
    void testBoyerMooreAlgoEfficiency() {

        long t1, t2, diffT;
        System.out.println("----- Tests d'efficacité de l'algorithme de Boyer-Moore : -----\n");
        int size = 1000;
        String pattern = "abcde";
        System.out.println(" -- Texte avec alphabet restreint {a,b,c,d} et motif \"abcde\"\n");
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateSequenceText(size);
            System.out.println("Taille du texte : " + size);
            cpt = 0; // reset the counter
            t1 = System.nanoTime();
            boyerMooreAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1; // in nanoseconds
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println("Nombre d'opérations/n (cpt/n) : " + (cpt / (double) size));
            System.out.println();
            size = size * 2;
        }
        System.out.println(" -- Texte avec alphabet restreint {a} et motif \"ab\"\n");
        size = 1000;
        pattern = "ab";
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateLettreText(size, 'a');
            System.out.println("Taille du texte : " + size);
            cpt = 0; // reset the counter
            t1 = System.nanoTime();
            boyerMooreAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1; // in nanoseconds
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println("Nombre d'opérations/n (cpt/n) : " + (cpt / (double) size));
            System.out.println();
            size = size * 2;
        }
        System.out.println(" -- Texte avec alphabet entier aleatoire et motif \"abf\"\n");
        size = 1000;
        pattern = "abf";
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateRandomText(size);
            System.out.println("Taille du texte : " + size);
            cpt = 0; // reset the counter
            t1 = System.nanoTime();
            boyerMooreAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1; // in nanoseconds
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println("Nombre d'opérations/n (cpt/n) : " + (cpt / (double) size));
            System.out.println();
            size = size * 2;
        }
        System.out.println("--------------------------------------------------");
    }
}
