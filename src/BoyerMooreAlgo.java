import java.util.ArrayList;
import java.util.Arrays;

class BoyerMooreAlgo {

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

    void testBoyerMooreAlgo() {

        System.out.println("----- Tests de l'algorithme de Boyer-Moore : -----");
        
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

        System.out.println("--------------------------------------------------");

    }

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

    ArrayList<Character> generateRandomText(int size) {
        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        ArrayList<Character> text = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            text.add(alphabet[(int) (Math.random() * alphabet.length)]);
        }

        return text;
    }

    void testBoyerMooreAlgoEfficiency() {
        
    }

}
