import java.util.Comparator;
import java.util.List;

public class WordComparators {

    private static int countChar(String s, char c){
        int count = 0;
        for(int i = 0; i<s.length();i++){
            if(s.charAt(i) == c){
                count++;
            }
        }
        return count;
    }

    private static int countChars(String  s, List<Character> chars){
        int count = 0;
        for(int i = 0; i<s.length();i++){
            if(chars.contains(s.charAt(i))){
                count++;
            }
        }
        return count;
    }

    /** Returns a comparator that orders strings by the number of lowercase 'x' characters (ascending). */
    public static Comparator<String> getXComparator() {
        // TODO: Implement this.
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return countChar(o1,'x') - countChar(o2,'x');
            }
        };
    }

    /** Returns a comparator that orders strings by the count of the given character (ascending). */
    public static Comparator<String> getCharComparator(char c) {
        // TODO: Implement this.
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return countChar(o1,c) -countChar(o2,c);
            }
        };
    }

    /** Returns a comparator that orders strings by the total count of the given characters (ascending). */
    public static Comparator<String> getCharListComparator(List<Character> chars) {
        // TODO: Implement this.
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return countChars(o1,chars) - countChars(o2,chars);
            }
        };
    }
}
