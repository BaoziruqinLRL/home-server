package com.yyhome.leetcode.easy;

/**
 * 206反转链表
 * @author miluo
 * @date 2020-03-02
 */
public class ReverseList {

    public static void main(String[] args){
        print(reverseList(createLink(1,2,3,4,5)));
        print(reverseList(createLink(5,4,3,2,1)));
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode newHead = new ListNode(head.val);
        head = head.next;
        for (;head != null;){
            ListNode currentNode = new ListNode(head.val);
            currentNode.next = newHead;
            newHead = currentNode;
            if (head.next != null) {
                head = head.next;
            }else{
                break;
            }
        }
        return newHead;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private static ListNode createLink(int... values){
        var count = 0;
        var node = new ListNode(values[count++]);
        var currentNode = node;
        for (;count < values.length;){
            currentNode.next = new ListNode(values[count++]);
            currentNode = currentNode.next;
        }
        return node;
    }

    private static void print(ListNode node){
        for (;node != null;){
            System.out.println(node.val + ",");
            node = node.next;
        }
    }
}
