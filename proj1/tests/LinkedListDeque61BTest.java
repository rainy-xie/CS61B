import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyAndSizeTest(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        //空链表测试
        assertThat(lld1.isEmpty()).isTrue();
        assertThat(lld1.size()).isEqualTo(0);

        //添加一个元素后
        lld1.addFirst("a");
        assertThat(lld1.isEmpty()).isFalse();
        assertThat(lld1.size()).isEqualTo(1);

        //再添加一个元素
        lld1.addLast("b");
        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("a");   // [a]
        lld1.addLast("b");   // [a, b]
        lld1.addLast("c");   // [a, b, c]

        // 正常情况
        assertThat(lld1.get(0)).isEqualTo("a");
        assertThat(lld1.get(1)).isEqualTo("b");
        assertThat(lld1.get(2)).isEqualTo("c");

        // 越界情况
        assertThat(lld1.get(-1)).isNull();
        assertThat(lld1.get(100)).isNull();
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("a");   // [a]
        lld1.addLast("b");   // [a, b]
        lld1.addLast("c");   // [a, b, c]

        // 正常情况
        assertThat(lld1.getRecursive(0)).isEqualTo("a");
        assertThat(lld1.getRecursive(1)).isEqualTo("b");
        assertThat(lld1.getRecursive(2)).isEqualTo("c");

        // 越界情况
        assertThat(lld1.getRecursive(-1)).isNull();
        assertThat(lld1.getRecursive(100)).isNull();
    }

    @Test
    public void removeFirstAndLastTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        // 空列表删除应该返回 null
        assertThat(lld1.removeFirst()).isNull();
        assertThat(lld1.removeLast()).isNull();

        // 添加元素 [a, b, c]
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addLast("c");

        // 删除第一个
        assertThat(lld1.removeFirst()).isEqualTo("a");
        assertThat(lld1.toList()).containsExactly("b", "c").inOrder();

        // 删除最后一个
        assertThat(lld1.removeLast()).isEqualTo("c");
        assertThat(lld1.toList()).containsExactly("b").inOrder();

        // 删到空
        assertThat(lld1.removeFirst()).isEqualTo("b");
        assertThat(lld1.isEmpty()).isTrue();
    }
}