public class Stack {
    // 定义内部节点
    private class Node{
        int item;
        Node next;

        Node(int i, Node n){
            item = i;
            next = n;
        }
    }

    // 哨兵节点+个数
    private Node sentinel;
    private int size;

    public Stack(){
        sentinel = new Node(33, null);
        size = 0;
    }

    public void push(int i){
        sentinel.next = new Node(i, sentinel.next);
        size += 1;
    }

    public int pop(){
        int result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return result;
    }

    public int size(){
        return size;
    }

    public int sum(){
        int result = 0;
        Node p = sentinel.next;
        while(p != null){
            result += p.item;
            p = p.next;
        }
        return result;
    }
}
