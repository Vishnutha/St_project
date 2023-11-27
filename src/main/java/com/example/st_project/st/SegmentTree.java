package com.example.st_project.st;

public class SegmentTree {
    private int[] tree;
    private int[] nums;
    private int n;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        // The size of the segment tree should be 2 * 2^ceil(log2(n)) - 1
        int treeSize = 2 * (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2))) - 1;
        this.tree = new int[treeSize];
        buildTree(0, 0, n - 1);
    }

    private void buildTree(int node, int start, int end) {
        if (start == end) {
            // Leaf node, store the value of the array element
            tree[node] = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            // Recursively build the left and right subtrees
            buildTree(2 * node + 1, start, mid);
            buildTree(2 * node + 2, mid + 1, end);
            // Update the value of the current node based on the left and right subtrees
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int query(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        // Case 1: No overlap
        if (right < start || left > end) {
            return 0;
        }
        // Case 2: Complete overlap
        if (left <= start && right >= end) {
            return tree[node];
        }
        // Case 3: Partial overlap
        int mid = start + (end - start) / 2;
        int leftSum = query(2 * node + 1, start, mid, left, right);
        int rightSum = query(2 * node + 2, mid + 1, end, left, right);
        return leftSum + rightSum;
    }

    public void update(int index, int newValue) {
        update(0, 0, n - 1, index, newValue);
    }

    private void update(int node, int start, int end, int index, int newValue) {
        // If the update index is out of the current node's range, return
        if (index < start || index > end) {
            return;
        }
        // If the current node is a leaf node, update its value
        if (start == end) {
            tree[node] = newValue;
        } else {
            int mid = start + (end - start) / 2;
            // Recursively update the left or right subtree
            update(2 * node + 1, start, mid, index, newValue);
            update(2 * node + 2, mid + 1, end, index, newValue);
            // Update the value of the current node based on the left and right subtrees
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}

//class Main {
//    public static void main(String[] args) {
//        int[] nums = {1, 3, 5, 7, 9, 11};
//        SegmentTree segmentTree = new SegmentTree(nums);
//
//        // Query the sum of elements in the range [1, 3]
//        int sum = segmentTree.query(1, 3);
//        System.out.println("Sum in the range [1, 3]: " + sum);
//
//        // Update the value at index 2 to 6
//        segmentTree.update(2, 6);
//
//        // Query the sum of elements in the range [1, 3] after the update
//        sum = segmentTree.query(1, 3);
//        System.out.println("Sum in the range [1, 3] after update: " + sum);
//    }
//}
