package com.yyhome.leetcode.data;

import lombok.Data;

/**
 * @author miluo
 * @date 2020-03-23
 */
@Data
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int val){
        this.val = val;
    }

    public static ListNode createLink(int... values){
        var count = 0;
        var node = new ListNode(values[count++]);
        var currentNode = node;
        for (;count < values.length;){
            currentNode.next = new ListNode(values[count++]);
            currentNode = currentNode.next;
        }
        return node;
    }

    public static void print(ListNode node){
        while (node != null){
            System.out.print(node.val + ",");
            node = node.next;
        }
        System.out.println();
    }
}
