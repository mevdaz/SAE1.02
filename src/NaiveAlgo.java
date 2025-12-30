import java.util.ArrayList;

/**
 * Implémentation de l'algorithme naïf de recherche de chaîne de caractères.
 * Inclut des tests d'efficacité et des méthodes utilitaires pour générer des textes de test.
 */


class NaiveAlgo {

    /**
     * Compteur global pour mesurer l'efficacité de l'algorithme.
     */
    long cpt;

    /**
     * Méthode principale pour exécuter les tests de l'algorithme naïf.
     */
    void principal() {
        testNaiveAlgo();
        testNaiveAlgoEfficiency();
    }


    /**
     * Génère un texte aléatoire de la taille spécifiée.
     * @param size La taille du texte à générer.
     * @return Une liste de caractères représentant le texte généré.
     */
    ArrayList<Character> generateText(int size) {
        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        ArrayList<Character> text = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            text.add(alphabet[(int) (Math.random() * alphabet.length)]);
        }
        return text;
    }


    /**
     * Implémente l'algorithme naïf pour trouver toutes les occurrences d'un motif dans un texte.
     * @param text Le texte dans lequel chercher le motif.
     * @param pattern Le motif à rechercher.
     * @return Une liste des indices de début de chaque occurrence du motif dans le texte.
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
     * Méthode de test pour l'algorithme naïf.
     */
    void testNaiveAlgo() {
        System.out.println("Test NaiveAlgo");
        ArrayList<Character> text = generateText(100000);
        TestCasNaiveAlgo(text, "toto");
        ArrayList<Character> text2 = generateText(100000);
        TestCasNaiveAlgo(text2, "tata");
    }


    /**
     * Teste un cas spécifique de l'algorithme naïf.
     * @param text Le texte dans lequel chercher le motif.
     * @param pattern Le motif à rechercher.
     */
    void TestCasNaiveAlgo(ArrayList<Character> text, String pattern) {
        System.out.println("Text : " + text);
        System.out.println("Pattern : " + pattern);
        ArrayList<Integer> tab = naiveAlgo(text, pattern);
        System.out.println("Tab : " + tab);
    }

    /**
     * Teste l'efficacité de l'algorithme naïf (à implémenter).
     */
    void testNaiveAlgoEfficiency() {
        long t1, t2, diffT;
        System.out.println("----- Tests d'efficacité de l'algorithme naïf : -----\n");
        int size = 1000;
        String pattern = "abcde";
        System.out.println(" -- Texte avec alphabet restreint {a,b,c,d} et motif \"abcde\"\n");
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateSequenceText(size);
            System.out.println("Taille du texte : " + size);
            cpt = 0; // reset le compteur
            t1 = System.nanoTime();
            naiveAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1; // en nanosecondes
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println();
            size = size * 2;
        }
        System.out.println(" -- Texte avec alphabet restreint {a} et motif \"ab\"\n");
        size = 1000;
        pattern = "ab";
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
            System.out.println();
            size = size * 2;
        }
        System.out.println(" -- Texte avec alphabet entier aléatoire et motif \"abf\"\n");
        size = 1000;
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
            System.out.println();
            size = size * 2;
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Génère un texte aléatoire avec un alphabet restreint pour les tests d'efficacité.
     * @param size La taille du texte à générer.
     * @return Une liste de caractères représentant le texte généré.
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
     * Génère une séquence de la même lettre de la taille spécifiée pour les tests d'efficacité.
     * @param size La taille du texte à générer.
     * @param lettre La lettre à répéter.
     * @return Une liste de caractères représentant le texte généré.
     */
    ArrayList<Character> generateLettreText(int size, char lettre) {
        ArrayList<Character> text = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            text.add(lettre);
        }
        return text;
    }
}

