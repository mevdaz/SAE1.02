import java.util.ArrayList;
import java.util.Arrays;

class BoyerMooreAlgo {

    /** Compteur global pour mesurer l'efficacité des algorithmes */
	long cpt;

    /**
     * Méthode principale pour exécuter les tests de l'algorithme de Boyer-Moore.
     */
    void principal() {

        testBoyerMooreAlgo();
        testBoyerMooreAlgoEfficiency();

    }

    /**
     * Calcule le décalage à effectuer en fonction du caractère en erreur
     * 
     * @param pattern le motif recherché
     * @param c le caractère en erreur
     * @param posErreur la position du caractère en erreur dans le motif
     * @return le décalage à effectuer
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
     * Implémente l'algorithme de Boyer-Moore pour rechercher toutes les occurrences
     * d'un motif dans un texte.
     * @param texte le texte dans lequel rechercher le motif
     * @param pattern le motif à rechercher
     * @return une liste des indices de début de chaque occurrence du motif dans le texte
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
            
            cpt++; // incrémentation du compteur

            if (j < 0) {
                result.add(indPattern);

                indPattern += 1;
                indFinPattern = indPattern + pattern.length() - 1;

            } else {
                int decale = decalage(pattern, texte.get(indPattern + j), j);

                indPattern += decale;
                indFinPattern = indPattern + pattern.length() - 1;
            }
        }

        return result;
    }

    /** 
     * Méthode de test pour l'algorithme de Boyer-Moore.
     */
    void testBoyerMooreAlgo() {

        System.out.println("\n----- Tests de l'algorithme de Boyer-Moore : -----\n");
        
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

        System.out.println("\n--------------------------------------------------\n");

    }

    /** 
     * Teste un cas spécifique de l'algorithme de Boyer-Moore.
     * @param texte le texte dans lequel rechercher le motif
     * @param pattern le motif à rechercher
     * @param resultAttendu la liste des indices attendus pour les occurrences du motif
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
            System.out.println("Test réussi pour le pattern \"" + pattern.toString() + "\".");
        } else {
            System.out.println("Test échoué pour le pattern \"" + pattern.toString() + "\". Résultat attendu : " 
                + resultAttendu.toString() + ", Résultat obtenu : " + result.toString());
        }

        
    }

    /**
     * Génère un texte aléatoire de la taille spécifiée.
     * @param size la taille du texte à générer
     * @return une liste de caractères représentant le texte généré
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
     * Génère un texte aléatoire avec un alphabet restreint pour tester l'efficacité.
     * @param size la taille du texte à générer
     * @return une liste de caractères représentant le texte généré
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
     * Génère une séquence de meme lettre de taille spécifiée pour tester l'efficacité.
     * @param size la taille du texte à générer
     * @param lettre la lettre à répéter
     * @return une liste de caractères représentant le texte généré
     */
    ArrayList<Character> generateLettreText(int size, char lettre) {

        ArrayList<Character> text = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            text.add(lettre);
        }

        return text;
    }

    /**
     * Teste l'efficacité de l'algorithme de Boyer-Moore.
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
            cpt = 0; // réinitialisation du compteur

            t1 = System.nanoTime();
            boyerMooreAlgo(texte, pattern);
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
            cpt = 0; // réinitialisation du compteur

            t1 = System.nanoTime();
            boyerMooreAlgo(texte, pattern);
            t2 = System.nanoTime();

            diffT = t2 - t1; // en nanosecondes

            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);

            System.out.println();

            size = size * 2;
        }

        System.out.println(" -- Texte avec alphabet entier aleatoire et motif \"abf\"\n");

        size = 1000;
        pattern = "abf";

        for (int i = 1; i <= 6; i++) {

            ArrayList<Character> texte = generateRandomText(size);

            System.out.println("Taille du texte : " + size);
            cpt = 0; // réinitialisation du compteur

            t1 = System.nanoTime();
            boyerMooreAlgo(texte, pattern);
            t2 = System.nanoTime();

            diffT = t2 - t1; // en nanosecondes

            System.out.println("Temps d'exécution : " + diffT + " ns");
            System.out.println("Nombre d'opérations (cpt) : " + cpt);

            System.out.println();

            size = size * 2;
        }

        System.out.println("--------------------------------------------------");
        
    }
}
