import java.util.ArrayList;

class BoyerMooreAlgo {
    void principal() {

        ArrayList<Character> text = generateText(1000000);
        System.out.println(text);
        String pattern = "toto";
        ArrayList<Integer> result = boyerMooreAlgo(text, pattern);
        System.out.println(result);

    }

    ArrayList<Character> generateText(int size) {
        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        ArrayList<Character> text = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            text.add(alphabet[(int) (Math.random() * alphabet.length)]);
        }

        text.add('t');
        text.add('o');
        text.add('t');
        text.add('o');

        return text;
    }

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
        
    }

    void testCasBoyerMooreAlgo() {
        
    }

    void testBoyerMooreAlgoEfficiency() {
        
    }

}
