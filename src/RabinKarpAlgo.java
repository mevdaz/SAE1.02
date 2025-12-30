import java.util.ArrayList;

/**
 * Implémentation de l'algorithme de Rabin-Karp pour la recherche de chaînes de caractères.
 * Inclut des tests d'efficacité et des méthodes utilitaires pour générer des textes de test.
 */
class RabinKarpAlgo {

    /**
     * Compteur global pour mesurer l'efficacité de l'algorithme.
     */
    long cpt;

    /**
     * Méthode principale pour exécuter les tests de l'algorithme Rabin-Karp.
     */
    void principal() {
        testRabinKarpAlgo();
        testRabinKarpAlgoEfficiency();
    }

    /**
     * Implémente l'algorithme de Rabin-Karp pour trouver toutes les occurrences d'un motif dans un texte.
     * @param texte Le texte dans lequel chercher le motif.
     * @param pattern Le motif à rechercher.
     * @return Une liste des indices de début de chaque occurrence du motif dans le texte.
     */
    ArrayList<Integer> rabinKarpAlgo(ArrayList<Character> texte, String pattern) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = texte.size();
        int m = pattern.length();
        if (m == 0 || n < m) return result;

        int base = 256; // taille de l'alphabet
        int mod = 1000000007; // nombre premier pour éviter les collisions

        // Calcul du hash du motif
        int patternHash = 0;
        int textHash = 0;
        int h = 1;
        cpt = 0;

        // Calcul de h = base^(m-1) % mod
        for (int i = 0; i < m - 1; i++) {
            h = (h * base) % mod;
        }

        // Calcul du hash initial du motif et du texte
        for (int i = 0; i < m; i++) {
            patternHash = (base * patternHash + pattern.charAt(i)) % mod;
            textHash = (base * textHash + texte.get(i)) % mod;
            cpt++;
        }

        // Recherche du motif dans le texte
        for (int i = 0; i <= n - m; i++) {
            cpt++;
            // Si les hash sont égaux, vérifier caractère par caractère
            if (patternHash == textHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    cpt++;
                    if (texte.get(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    result.add(i);
                }
            }
            // Calcul du hash du prochain sous-texte
            if (i < n - m) {
                textHash = (base * (textHash - texte.get(i) * h) + texte.get(i + m)) % mod;
                if (textHash < 0) textHash += mod;
            }
        }
        return result;
    }

    /**
     * Méthode de test pour l'algorithme Rabin-Karp.
     */
    void testRabinKarpAlgo() {
        // System.out.println("\n----- Tests de l'algorithme de Rabin-Karp : -----\n");
        ArrayList<Character> texte1 = new ArrayList<>();
        for (char c : "Il a regardé dedans, est entré dedans, puis a caché la clé dedans.".toCharArray()) texte1.add(c);
        String pattern1 = "dedans";
        ArrayList<Integer> resultAttendu1 = new ArrayList<>();
        resultAttendu1.add(13); resultAttendu1.add(31); resultAttendu1.add(59);
        testCasRabinKarpAlgo(texte1, pattern1, resultAttendu1);

        ArrayList<Character> texte2 = new ArrayList<>();
        for (char c : "Il est entré dans la maison, a nettoyé la maison, puis a quitté la maison.".toCharArray()) texte2.add(c);
        String pattern2 = "maison";
        ArrayList<Integer> resultAttendu2 = new ArrayList<>();
        resultAttendu2.add(21); resultAttendu2.add(42); resultAttendu2.add(67);
        testCasRabinKarpAlgo(texte2, pattern2, resultAttendu2);

        ArrayList<Character> texte3 = new ArrayList<>();
        for (char c : "Le soleil brille sur la mer et les oiseaux chantent joyeusement.".toCharArray()) texte3.add(c);
        String pattern3 = "ordinateur";
        ArrayList<Integer> resultAttendu3 = new ArrayList<>();
        testCasRabinKarpAlgo(texte3, pattern3, resultAttendu3);
        // System.out.println("\n--------------------------------------------------\n");
    }

    /**
     * Teste un cas spécifique de l'algorithme Rabin-Karp.
     */
    void testCasRabinKarpAlgo(ArrayList<Character> texte, String pattern, ArrayList<Integer> resultAttendu) {
        ArrayList<Integer> result = rabinKarpAlgo(texte, pattern);
        boolean equals = true;
        if (result.size() != resultAttendu.size()) {
            equals = false;
        } else {
            for (int i = 0; i < result.size(); i++) {
                if (!result.get(i).equals(resultAttendu.get(i))) {
                    equals = false;
                    break;
                }
            }
        }
        if (equals) {
            System.out.println("Test passed for pattern \"" + pattern + "\".");
        } else {
            System.out.println("Test failed for pattern \"" + pattern + "\". Expected: " + resultAttendu + ", Got: " + result);
        }
    }

    /**
     * Teste l'efficacité de l'algorithme Rabin-Karp.
     */
    void testRabinKarpAlgoEfficiency() {
        long t1, t2, diffT;
        System.out.println("----- Tests d'efficacité de l'algorithme de Rabin-Karp : -----\n");
        int size = 1000;
        String pattern = "abcde";
        System.out.println(" -- Texte avec alphabet restreint {a,b,c,d} et motif \"abcde\"\n");
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateSequenceText(size);
            System.out.println("Taille du texte : " + size);
            cpt = 0;
            t1 = System.nanoTime();
            rabinKarpAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1;
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
            cpt = 0;
            t1 = System.nanoTime();
            rabinKarpAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1;
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println();
            size = size * 2;
        }
        System.out.println(" -- Texte avec alphabet entier aléatoire et motif \"abf\"\n");
        size = 1000;
        pattern = "abf";
        for (int i = 1; i <= 6; i++) {
            ArrayList<Character> texte = generateRandomText(size);
            System.out.println("Taille du texte : " + size);
            cpt = 0;
            t1 = System.nanoTime();
            rabinKarpAlgo(texte, pattern);
            t2 = System.nanoTime();
            diffT = t2 - t1;
            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);
            System.out.println();
            size = size * 2;
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Génère un texte aléatoire de la taille spécifiée.
     * @param size La taille du texte à générer.
     * @return Une liste de caractères représentant le texte généré.
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
