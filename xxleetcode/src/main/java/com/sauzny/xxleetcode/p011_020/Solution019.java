package com.sauzny.xxleetcode.p011_020;


import java.util.ArrayList;
import java.util.List;

/***************************************************************************
 *
 * @时间: 2019/2/20 - 11:18
 *
 * @描述: TODO
 *
 ***************************************************************************/
public class Solution019 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(head == null) return null;

        List<ListNode> nodes = nodes(head);

        int targetIndex = nodes.size() - n;

        if(targetIndex == 0){
            if (nodes.size() <= 1) return null;
            return nodes.get(1);
        } else if(targetIndex > 0 && targetIndex < nodes.size() - 1){
            nodes.get(targetIndex-1).next = nodes.get(targetIndex+1);
        } else if(targetIndex == nodes.size() - 1){
            nodes.get(targetIndex-1).next = null;
        }

        return nodes.get(0);
    }

    private List<ListNode> nodes(ListNode head){
        List<ListNode> nodes = new ArrayList<ListNode>();
        ListNode temp = head;
        while(temp != null){
            nodes.add(temp);
            if(temp.next != null){
                temp = temp.next;
            }else{
                break;
            }
        }
        return nodes;
    }

    public void print(ListNode head){
        if(head != null){
            System.out.print(head.val);
            if(head.next != null) {
                System.out.print(" -> ");
                print(head.next);
            }else{
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Solution019 solution = new Solution019();

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        solution.print(n1);

        long a = System.currentTimeMillis();
        ListNode result = solution.removeNthFromEnd(n1, 2);
        long b = System.currentTimeMillis();
        //System.out.println(result);
        solution.print(result);
        System.out.println("timing : " + (b-a));
    }
}

