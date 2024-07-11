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

    public static void main(String[] args) {
        // Constructing a sample binary tree:
        //         1
        //        / \
        //       2   3
        //      / \   \
        //     4   5   6

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        // Performing iterative preorder traversal
        List<Integer> result = IterativePreorderTraversal(root);

        // Printing the result
        System.out.println("Preorder Traversal: " + result);
    }
}
