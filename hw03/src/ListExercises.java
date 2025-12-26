import java.util.ArrayList;
import java.util.List;

public class ListExercises {
    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // TODO: Implement this method
        int sum = 0;
        for(Integer i : L){
            sum +=i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Implement this method
        List<Integer> newL = new ArrayList<>();
        for(Integer i : L){
            if(i%2 ==0){
                newL.add(i);
            }
        }

        return newL;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Implement this method
        List<Integer> newL = new ArrayList<>();
        for(Integer i : L1){
            for(Integer j :L2){
                if(i.equals(j)){
                    newL.add(i);
                }
            }
        }
        return newL;
    }

    public static int countOccurrencesOfWord(List<String> words, String w) {
        // TODO: Implement this method
        int count = 0;
        for(String word : words){
            if(word.equals(w)){
                count++;
            }
        }
        return count;
    }

    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Implement this method
        int count = 0;
        for(String word : words){
            for(char w :word.toCharArray())
            {
                if(w == c){
                    count++;
                }
            }
        }
        return count;
    }
}
