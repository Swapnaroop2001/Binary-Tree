import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * BinaryTree
 */
public class BinaryTree {
    
    /**
     * Node
     */
    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int value){
            this.value=value;
        }

        Node(int value,Node left,Node right){
            this.value=value;
            this.left=left;
            this.right=right;
        }
        
    }

    //Iterative Preorder Traversal
    public static List<Integer> IterativePreorderTraversal(Node root){
        List<Integer> list=new ArrayList<>();
        if (root==null) {
            return list;
        }
        Stack<Node> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            root=st.pop();
            list.add(root.value);
            if (root.right!=null) {
                st.push(root.right);
            }
            if (root.left!=null) {
                st.push(root.left);
            }
        }
        return list;
    }

    //Iterative Inorder Traversal
    public static List<Integer> IterativeInorderTraversal(Node root) {
        List<Integer> list=new ArrayList<>();
        if (root==null) {
            return list;
        }
        Stack <Node> st= new Stack<>();
        Node node=root;

        while (true) {
            if (node!=null) {
                st.push(node);
                node=node.left;
            }else{
                if (st.isEmpty()) {
                    break;
                }
                node=st.pop();
                list.add(node.value);
                node=node.right;
            }
        }
        return list;
    }

    //Iterative Postorder Traversal
    public static List<Integer> IterativePostorderTraversal(Node root) {
        List <Integer> list= new ArrayList<>();
        if (root==null) {
            return list;
        }
        Stack<Node> st1=new Stack<>();
        Stack<Node> st2=new Stack<>();
        st1.push(root);

        while (!st1.isEmpty()) {
            root=st1.pop();
            st2.push(root);
            if (root.left!=null) {st1.push(root.left);}
            if (root.right!=null) {st1.push(root.right);}
        }
        while (!st2.isEmpty()) {
            list.add(st2.pop().value);
        }
        return list;
    }

    //Maximu Depth of Array
    public static int MaxDepth(Node root) {
        if (root==null) {
            return 0;
        }
        int lh=MaxDepth(root.left);
        int rh=MaxDepth(root.right);
        return 1+Math.max(lh, rh);
    }

    //Check if tree is balanced
    public static boolean isBalanced(Node root){
        return dfsHeight(root) !=-1;
    }
    public static int dfsHeight(Node root){
        if (root==null) {
            return 0;
        }
        int lh=dfsHeight(root.left);
        if (lh==-1) { return -1;}

        int rh=dfsHeight(root.right);
        if (rh==-1) { return -1;}

        if (Math.abs(lh-rh)>1) return -1;

        return Math.max(lh, rh)+1;
    }



    public static void main(String[] args) {
        // Constructing a sample binary tree:
        //         1
        //        / \
        //       2   3
        //      / \   \
        //     4   5   6
        // 4, 2, 5, 1, 3, 6

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        // Performing iterative preorder traversal
        boolean  result = isBalanced(root);

        // Printing the result
        System.out.println(result);
    }
}
