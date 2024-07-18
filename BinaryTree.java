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

        Node(int value) {
            this.value = value;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    // Iterative Preorder Traversal
    public static List<Integer> IterativePreorderTraversal(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            root = st.pop();
            list.add(root.value);
            if (root.right != null) {
                st.push(root.right);
            }
            if (root.left != null) {
                st.push(root.left);
            }
        }
        return list;
    }

    // Iterative Inorder Traversal
    public static List<Integer> IterativeInorderTraversal(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> st = new Stack<>();
        Node node = root;

        while (true) {
            if (node != null) {
                st.push(node);
                node = node.left;
            } else {
                if (st.isEmpty()) {
                    break;
                }
                node = st.pop();
                list.add(node.value);
                node = node.right;
            }
        }
        return list;
    }

    // Iterative Postorder Traversal
    public static List<Integer> IterativePostorderTraversal(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(root);

        while (!st1.isEmpty()) {
            root = st1.pop();
            st2.push(root);
            if (root.left != null) {
                st1.push(root.left);
            }
            if (root.right != null) {
                st1.push(root.right);
            }
        }
        while (!st2.isEmpty()) {
            list.add(st2.pop().value);
        }
        return list;
    }

    // Height or Maximu Depth of tree
    public static int findHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = findHeight(root.left);
        int rh = findHeight(root.right);
        return 1 + Math.max(lh, rh);
    }

    // Check if tree is balanced
    public static boolean isBalanced(Node root) {
        return dfsHeight(root) != -1;
    }

    public static int dfsHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = dfsHeight(root.left);
        if (lh == -1) {
            return -1;
        }

        int rh = dfsHeight(root.right);
        if (rh == -1) {
            return -1;
        }

        if (Math.abs(lh - rh) > 1)
            return -1;

        return Math.max(lh, rh) + 1;
    }

    // Diameter of the tree
    public static int findDiameter(Node root) {
        int[] Diameter = new int[1];
        findHeight(root, Diameter);
        return Diameter[0];
    }

    public static int findHeight(Node root, int[] Diameter) {
        if (root == null) {
            return 0;
        }
        int lh = findHeight(root.left, Diameter);
        int rh = findHeight(root.right, Diameter);

        Diameter[0] = Math.max(Diameter[0], lh + rh);
        return Math.max(lh, rh) + 1;
    }

    // Maximum sum path
    public static int maxPathSum(Node root) {
        int maxValue[] = new int[1];
        maxValue[0] = Integer.MIN_VALUE;
        maxPathDown(root, maxValue);
        return maxValue[0];
    }

    public static int maxPathDown(Node root, int[] maxValue) {
        if (root == null) {
            return 0;
        }
        int lsum = Math.max(0, maxPathDown(root.left, maxValue));
        int Rsum = Math.max(0, maxPathDown(root.right, maxValue));
        maxValue[0] = Math.max(maxValue[0], root.value + lsum + Rsum);
        return root.value + Math.max(lsum, Rsum);
    }

    public static void main(String[] args) {
        // Constructing a sample binary tree:
        //         10
        //        /  \
        //       5    -3
        //      / \     \
        //     3   2     11
        //    / \   \   
        //   3  -2   1 

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(-3);
        root.left.left = new Node(3);
        root.left.right = new Node(2);
        root.right.right = new Node(11);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(-2);
        root.left.right.right = new Node(1);

        // Performing iterative preorder traversal
        int result = maxPathSum(root);

        // Printing the result
        System.out.println(result);
    }
}
