package org.demo.mypackage;

import java.util.*;
import java.util.stream.Stream;


public class Main {



    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.addNode(7);
        tree.addNode(4);
        tree.addNode(3);
        tree.addNode(10);
        tree.addNode(9);
        tree.addNode(8);
        tree.addNode(11);
        tree.addNode(2);
        tree.addNode(5);



//        tree.dfs();
        int size = 26;
        int[] arr = new int[size+1];
        System.out.println(calcFibonacci(size, arr));



    }

    private static int calcFibonacci(int n, int[] arr) {
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        } else if(arr[n] != 0) {
            return arr[n];
        } else {
            int fib = calcFibonacci(n-1,arr) + calcFibonacci(n-2,arr);
            arr[n] = fib;
            return fib;
        }
    }


}

class Node implements MyFunction{
    Node right;
    Node left;
    int val;

    public Node(int val) {
        right = null;
        left = null;
        this.val = val;
    }

    @Override
    public void printText(String text) {

    }

    public static void printEverything(String text) {
        System.out.println("hi");
    }
}

class BinaryTree {
    Node root;

    private Node addRecursive(Node current, int val) {
        if(current == null) {
            return new Node(val);
        }

        if(current.val > val) {
            current.left = addRecursive(current.left, val);
        } else if(current.val < val) {
            current.right = addRecursive(current.right, val);
        } else {
            return current; // already exists
        }

        return current;
    }

    public void addNode(int val) {
        root = addRecursive(root, val);
    }

    private boolean containsNodeRecursive(Node current, int val) {
        if(current == null) {
            return false;
        }

        if(current.val > val) {
            return containsNodeRecursive(current.left, val);
        } else if(current.val < val) {
            return containsNodeRecursive(current.right, val);
        } else {
            return true;
        }

    }

    public boolean containsNode(int val) {
        return containsNodeRecursive(root, val);
    }

    private Node deleteRecursive(Node current, int val) {
        if(current == null) {
            return null;
        }

        if(current.val > val) {
            current.left = deleteRecursive(current.left, val);
            return current;
        } else if(current.val < val) {
            current.right = deleteRecursive(current.right, val);
            return current;
        } else {
            if(current.left == null && current.right == null) {
                return null;
            } else if(current.left == null) {
                return current.right;
            } else if(current.right == null) {
                return current.left;
            } else {
                int newVal = findSmallestNodeInSubTree(current.right);
                current.val = newVal;
                current.right = deleteRecursive(current.right, newVal);
                return current;
            }
        }
    }

    private int findSmallestNodeInSubTree(Node small) {
        if(small.left == null) {
            return small.val;
        } else {
            return findSmallestNodeInSubTree(small.left);
        }
    }

    public void delete(int val) {
        root = deleteRecursive(root, val);
    }

    public void bfs() {
        if(root == null) {
            return;
        }

        Queue<Node>  nodes = new LinkedList<>();
        nodes.add(root);

        while(!nodes.isEmpty()) {
            Node curNode = nodes.remove();

            if(curNode.left != null) {
                nodes.add(curNode.left);
            }

            if(curNode.right != null) {
                nodes.add(curNode.right);
            }
        }
    }

    public void dfs() {
        if(root == null) {
            return;
        }

        Stack<Node> nodes = new Stack<>();
        Set<Integer> set = new HashSet<>();
        nodes.push(root);

        while(!nodes.isEmpty()) {
            Node curNode = nodes.peek();

            if(set.contains(curNode.val)){
                nodes.pop();
            } else {
                if(curNode.left != null && !set.contains(curNode.left.val)) {
                    nodes.push(curNode.left);
                } else {
                    set.add(curNode.val);
                    System.out.println(curNode.val);
                    if(curNode.right != null) {
                        nodes.push(curNode.right);
                    }
                }
            }
        }
    }

}