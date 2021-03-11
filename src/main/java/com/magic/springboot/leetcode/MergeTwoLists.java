package com.magic.springboot.leetcode;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //判空
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        //遍历两个链表  如果小于则向下遍历
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                pre.next = l2;
                l2 = l2.next;

            } else {
                pre.next = l1;
                l1 = l1.next;
            }

            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;

        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
