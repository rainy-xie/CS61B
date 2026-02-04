import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    @DisplayName("addLast: 基本测试")
    public void testAddLastBasic() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("a");   // [a]
        ad.addLast("b");   // [a, b]
        ad.addLast("c");   // [a, b, c]

        assertThat(ad.toList()).containsExactly("a", "b", "c").inOrder();
    }

    @Test
    @DisplayName("addFirst: 基本测试")
    public void testAddFirstBasic() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("a");  // [a]
        ad.addFirst("b");  // [b, a]
        ad.addFirst("c");  // [c, b, a]

        assertThat(ad.toList()).containsExactly("c", "b", "a").inOrder();
    }

    @Test
    @DisplayName("addFirst 和 addLast 混合使用")
    public void testAddFirstAndAddLast() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("a");   // [a]
        ad.addFirst("b");  // [b, a]
        ad.addLast("c");   // [b, a, c]
        ad.addFirst("d");  // [d, b, a, c]

        assertThat(ad.toList()).containsExactly("d", "b", "a", "c").inOrder();
    }

    @Test
    @DisplayName("add_first_from_empty: 空队列添加")
    public void testAddFirstFromEmpty() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addFirst(1);

        assertThat(ad.toList()).containsExactly(1);
    }

    @Test
    @DisplayName("add_last_from_empty: 空队列添加")
    public void testAddLastFromEmpty() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(1);

        assertThat(ad.toList()).containsExactly(1);
    }

    @Test
    @DisplayName("removeFirst: 基本测试")
    public void testRemoveFirstBasic() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("a");
        ad.addLast("b");
        ad.addLast("c");   // [a, b, c]

        assertThat(ad.removeFirst()).isEqualTo("a");
        assertThat(ad.toList()).containsExactly("b", "c").inOrder();
    }

    @Test
    @DisplayName("removeLast: 基本测试")
    public void testRemoveLastBasic() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("a");
        ad.addLast("b");
        ad.addLast("c");   // [a, b, c]

        assertThat(ad.removeLast()).isEqualTo("c");
        assertThat(ad.toList()).containsExactly("a", "b").inOrder();
    }

    @Test
    @DisplayName("remove 到空队列")
    public void testRemoveToEmpty() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("a");

        assertThat(ad.removeFirst()).isEqualTo("a");
        assertThat(ad.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("空队列 remove 返回 null")
    public void testRemoveFromEmpty() {
        Deque61B<String> ad = new ArrayDeque61B<>();

        assertThat(ad.removeFirst()).isNull();
        assertThat(ad.removeLast()).isNull();
    }

    @Test
    @DisplayName("remove 后再 add")
    public void testAddAfterRemove() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("a");
        ad.addLast("b");
        ad.removeFirst();     // [b]
        ad.addFirst("c");     // [c, b]

        assertThat(ad.toList()).containsExactly("c", "b").inOrder();
    }

    @Test
    @DisplayName("addFirst 触发扩容")
    public void testAddFirstTriggerResize() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // 添加超过 8 个元素，触发扩容
        for (int i = 0; i < 20; i++) {
            ad.addFirst(i);
        }

        assertThat(ad.size()).isEqualTo(20);

        // 检查顺序是否正确：[19, 18, 17, ..., 1, 0]
        for (int i = 0; i < 20; i++) {
            assertThat(ad.get(i)).isEqualTo(19 - i);
        }
    }

    @Test
    @DisplayName("addLast 触发扩容")
    public void testAddLastTriggerResize() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        for (int i = 0; i < 20; i++) {
            ad.addLast(i);
        }

        assertThat(ad.size()).isEqualTo(20);
        assertThat(ad.toList()).containsExactly(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19
        ).inOrder();
    }

    @Test
    @DisplayName("remove 触发缩容")
    public void testRemoveTriggerResizeDown() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // 先添加很多元素
        for (int i = 0; i < 100; i++) {
            ad.addLast(i);
        }

        // 删除大部分元素
        for (int i = 0; i < 95; i++) {
            ad.removeFirst();
        }

        // 应该还剩 5 个元素：[95, 96, 97, 98, 99]
        assertThat(ad.size()).isEqualTo(5);
        assertThat(ad.toList()).containsExactly(95, 96, 97, 98, 99).inOrder();
    }

    @Test
    @DisplayName("扩容后再缩容")
    public void testResizeUpThenDown() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // 添加触发扩容
        for (int i = 0; i < 32; i++) {
            ad.addLast(i);
        }

        // 删除触发缩容
        for (int i = 0; i < 28; i++) {
            ad.removeFirst();
        }

        // 应该还剩：[28, 29, 30, 31]
        assertThat(ad.size()).isEqualTo(4);
        assertThat(ad.toList()).containsExactly(28, 29, 30, 31).inOrder();
    }

    @Test
    @DisplayName("iterator: 遍历测试")
    public void testIterator() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("a");
        ad.addLast("b");
        ad.addLast("c");

        StringBuilder result = new StringBuilder();
        for (String s : ad) {
            result.append(s);
        }

        assertThat(result.toString()).isEqualTo("abc");
    }

    @Test
    @DisplayName("equals: 相同内容")
    public void testEqualsTrue() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad1.addLast("a");
        ad1.addLast("b");
        ad2.addLast("a");
        ad2.addLast("b");

        assertThat(ad1).isEqualTo(ad2);
    }

    @Test
    @DisplayName("equals: 不同内容")
    public void testEqualsFalse() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad1.addLast("a");
        ad2.addLast("b");

        assertThat(ad1).isNotEqualTo(ad2);
    }

    @Test
    @DisplayName("equals: 不同大小")
    public void testEqualsDifferentSize() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad1.addLast("a");
        ad1.addLast("b");
        ad2.addLast("a");

        assertThat(ad1).isNotEqualTo(ad2);
    }

    @Test
    @DisplayName("toString: 格式测试")
    public void testToString() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        assertThat(ad.toString()).isEqualTo("[front, middle, back]");
    }

    @Test
    @DisplayName("toString: 空队列")
    public void testToStringEmpty() {
        Deque61B<String> ad = new ArrayDeque61B<>();

        assertThat(ad.toString()).isEqualTo("[]");
    }
}
