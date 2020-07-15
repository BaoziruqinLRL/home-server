package com.yyhome.leetcode.data;

import lombok.Data;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author miluo
 * @date 2020-07-15
 */
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    TreeNode(int x) { val = x; }

    public static TreeNode createTree(Integer... values){
        int index = 1;
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);
        while (index < values.length){
            var currentNode = queue.poll();
            if (currentNode != null) {
                if (values[index] != null) {
                    currentNode.left = new TreeNode(values[index++]);
                    queue.offer(currentNode.left);
                }else{
                    index++;
                }
                if (index < values.length) {
                    if (values[index] != null) {
                        currentNode.right = new TreeNode(values[index++]);
                        queue.offer(currentNode.right);
                    }else{
                        index++;
                    }
                }
            }
        }
        return root;
    }
}
