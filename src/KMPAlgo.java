import java.util.ArrayList;

class KMPAlgo {

    void principal() {
        System.out.println("Test KMP");
        testKmpAlgo();
        testKmpAlgoEfficiency();
    }

    ArrayList<Integer> kmpAlgo(ArrayList<Character> text, String pattern) {

        ArrayList<Integer> result = new ArrayList<>();

        int n = text.size();
        int m = pattern.length();

        if (m == 0 || n == 0 || m > n) {
            return result;
        }

        int[] pi = buildPrefixTable(pattern);

        int i = 0;
        int j = 0;

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

    void testKmpAlgo() {

        ArrayList<Character> text = new ArrayList<>();
        String s = "ABABAABCBAB";

        for (int k = 0; k < s.length(); k++) {
            text.add(s.charAt(k));
        }

        String pattern = "ABABACA";

        ArrayList<Integer> res = kmpAlgo(text, pattern);

        System.out.println("Test KMP standard :");
        System.out.println("Texte   : " + s);
        System.out.println("Motif   : " + pattern);
        System.out.println("Résultat: " + res);
        System.out.println();
    }

    void testCasKmpAlgo() {

        ArrayList<Character> text = new ArrayList<>();
        String s = "ABABAABCBAB";

        for (int k = 0; k < s.length(); k++) {
            text.add(s.charAt(k));
        }

        String pattern = "BA";

        ArrayList<Integer> res = kmpAlgo(text, pattern);

        System.out.println("TEST CAS KMP :");
        System.out.println("Texte   : " + s);
        System.out.println("Motif   : " + pattern);
        System.out.println("Résultat: " + res);
        System.out.println();
    }

    void testKmpAlgoEfficiency() {

        ArrayList<Character> text = new ArrayList<>();

        // texte très long pour mesurer, ex : 100000 'A' puis un 'B'
        int size = 50000;
        for (int i = 0; i < size; i++) {
            text.add('A');
        }
        text.add('B');

        String pattern = "AAAAAAAAAAB";

        long start = System.nanoTime();
        kmpAlgo(text, pattern);
        long end = System.nanoTime();

        System.out.println("Test d'efficacité KMP :");
        System.out.println("Temps (ns) : " + (end - start));
        System.out.println();
    }

}
