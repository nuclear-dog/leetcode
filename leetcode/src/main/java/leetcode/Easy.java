package leetcode;

public class Easy {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for(int i = 0; i<words.length; i++){
            if (words[i].length()<searchWord.length()) continue;
            int j = 0;
            for(; j<searchWord.length(); j++){
                if (words[i].charAt(j) != searchWord.charAt(j)) break;
            }
            if (j == searchWord.length()) return i+1;
        }
        return -1;
    }
}
