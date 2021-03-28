package com.magic.springboot.leetcode;

public class HasCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode i = head;
        ListNode j = head.next;

        while (j != null && j.next != null) {
            if (i == j) {
                return true;
            }
            i = i.next;
            j = j.next.next;
        }

        return false;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
