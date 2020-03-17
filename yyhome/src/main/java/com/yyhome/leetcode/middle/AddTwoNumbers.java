package com.yyhome.leetcode.middle;

/**
 * 2 两数相加
 * @author miluo
 * @date 2020-03-17
 */
public class AddTwoNumbers {

    public static void main(String[] args){
        // 342 + 465 = 807
        print(addTwoNumbers(buildNode(new int[]{3,4,2}),buildNode(new int[]{4,6,5})));
        // 3416
        print(addTwoNumbers(buildNode(new int[]{1,0,9,5}),buildNode(new int[]{2,3,2,1})));
        // 10
        print(addTwoNumbers(buildNode(new int[]{5}),buildNode(new int[]{5})));
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进位
        int carry = 0;
        ListNode headNode = null;
        ListNode currentNode = null;
        while (l1 != null || l2 != null){
            var node = new ListNode((l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry);
            if (node.val > 9){
                carry = 1;
                node.val -= 10;
            }else{
                carry = 0;
            }
            headNode = headNode == null ? node : headNode;
            if (currentNode == null){
                currentNode = node;
            }else{
                currentNode.next = node;
                currentNode = node;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry == 1){
            currentNode.next = new ListNode(1);
        }
        return headNode;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private static void print(ListNode node){
        while (node != null){
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("");
    }

    private static ListNode buildNode(int[] nums){
        var head = new ListNode(nums[nums.length - 1]);
        ListNode nextNode = head;
        for (int index = nums.length - 2; index >=0; index--){
            var node = new ListNode(nums[index]);
            nextNode.next = node;
            nextNode = node;
        }
        return head;
    }
 }
