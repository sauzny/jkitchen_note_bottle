	package com.sauzny.xxleetcode.p001_010;

/**
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

Input: (3 -> 4 -> 5) + (3 -> 8)
Output: 6 -> 2 -> 6

 */

public class Solution002 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    
    public static void main(String[] args) {
    	
    	ListNode l1 = new ListNode(3);
    	ListNode l3 = new ListNode(4);
    	ListNode l5 = new ListNode(5);
    	
    	ListNode l2 = new ListNode(3);
    	ListNode l4 = new ListNode(8);
    	
    	l1.next = l3;
    	l3.next = l5;
    	
    	l2.next = l4;
    	
    	
    	Solution002 solution002 = new Solution002();

    	ListNode r = solution002.addTwoNumbers(l1, l2);
    	

    	System.out.println(r.val);
    	System.out.println(r.next.val);
    	System.out.println(r.next.next.val);
	}
    
}


/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}