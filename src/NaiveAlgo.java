import java.util.ArrayList;

class NaiveAlgo {
    void principal() {
        ArrayList<Character> text = generateText(100000);
        System.out.println(text);
        String pattern = "toto";
        ArrayList<Integer> tab = naiveAlgo(text, pattern);
        System.out.println(tab);
    }

    ArrayList<Character> generateText(int size) {
        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        ArrayList<Character> text = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            text.add(alphabet[(int) (Math.random() * alphabet.length)]);
        }

        return text;
    }

    ArrayList<Integer> naiveAlgo(ArrayList<Character> text, String pattern) {
        ArrayList<Integer> tab = new ArrayList<>();
        for (int i = 0; i < (text.size() - pattern.length()); i++) {
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

    void testNaiveAlgo() {
        System.out.println("Test NaiveAlgo");
        ArrayList<Character> text = generateText(100000);
        TestCasNaiveAlgo(text, "toto");
        ArrayList<Character> text2 = generateText(100000);
        TestCasNaiveAlgo(text2, "tata");
    }

    void TestCasNaiveAlgo(ArrayList<Character> text, String pattern) {
        System.out.println("Text : " + text);
        System.out.println("Pattern : " + pattern);
        ArrayList<Integer> tab = naiveAlgo(text, pattern);
        System.out.println("Tab : " + tab);
    }

    void testNaiveAlgoEfficiency() {

    }
}
