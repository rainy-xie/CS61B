public class StackClient {
    public static Stack flipped(Stack s){
        Stack result = new Stack();
        while(s.size() > 0){
            int temp = s.pop();
            result.push(temp);
        }
        return result;
    }
}
