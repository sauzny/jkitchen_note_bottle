package com.sauzny.xxleetcode.p0021_0030;

/***************************************************************************
 *
 * @时间: 2019/2/26 - 16:54
 *
 * @描述: TODO
 *
 ***************************************************************************/
public class Solution021 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode result = new ListNode(0);
        ListNode cur = result;

        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }

            cur = cur.next;

        }

        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }

        return result.next;
    }

    public void print(ListNode head) {
        if (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
                print(head.next);
            } else {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(4);
        ListNode l3 = new ListNode(6);
        ListNode l5 = new ListNode(7);
        l1.next = l3;
        l3.next = l5;

        ListNode l2 = new ListNode(1);
        ListNode l4 = new ListNode(3);
        ListNode l6 = new ListNode(6);
        l2.next = l4;
        l4.next = l6;

        Solution021 solution = new Solution021();
        long a = System.currentTimeMillis();
        ListNode result = solution.mergeTwoLists(l1, l2);
        long b = System.currentTimeMillis();
        //System.out.println(result);
        solution.print(l1);
        solution.print(l2);
        solution.print(result);
        System.out.println("timing : " + (b - a));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}