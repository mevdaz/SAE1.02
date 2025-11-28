import java.util.ArrayList;

class NaiveAlgo {
    void principal() {

    }

    void naiveAlgo() {
        String pattern = "";
        String text = "";
        ArrayList<Integer> tab = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == pattern.charAt(0)) {
                int j = 1;
                while (j < pattern.length() && text.charAt(i + j) == pattern.charAt(j)) {
                    j++;
                }
                if (j == pattern.length()) {
                    tab.add(i);
                }
            }
        }
    }

}