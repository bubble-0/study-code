package com.magic.springboot.leetcode;

public class ReverseList {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;

        reverseList(a);


    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null; //当前节点
        ListNode curr = head; //head节点遍历到的节点
        ListNode tmp;
        while (curr != null && curr.next != null) {
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
