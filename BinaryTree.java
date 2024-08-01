import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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

    //Check if trees are same
    public static boolean isSameTree(Node p, Node q) {
        if (p==null && q==null) {
            return p==q;
        }
        return (p.value)==(q.value) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //103. Binary Tree Zigzag Level Order Traversal
    public static List<List<Integer>> zigzagLevelOrder(Node root) {
        List <List<Integer>> result=new ArrayList<>() ;
        if (root==null) {
            return result;
        }
        boolean LeftToRight=true;
        Queue<Node> queue = new LinkedList<>(); 
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size=queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node node= queue.poll();
                if (LeftToRight) {
                    level.addLast(node.value);
                }else{
                    level.addFirst(node.value);
                }

                if (node.left!=null) { queue.offer(node.left); }
                if (node.right!=null) { queue.offer(node.right); }
            }
            result.add(level);
            LeftToRight= !LeftToRight;
        }
        return result;
    }

    //Boundry Traversal 
    public static ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList <Integer> list= new ArrayList<>();
        if (root==null) {
            return list;
        }
        if (!isLeaf(root)) {
            list.add(root.value);
        }
        addLeftBoundry(root, list);
        addLeaf(root, list);
        addRightBoundry(root, list);
        return list;
    }
    private static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }
    private static void addLeftBoundry(Node root, ArrayList <Integer> list){
        Node cur= root.left;
        while (cur!=null) {
            if (!isLeaf(cur)) list.add(cur.value); 
            if (cur.left!=null) cur=cur.left;
            else cur=cur.right;
        }
    }
    private static void addRightBoundry(Node root, ArrayList <Integer> list){
        ArrayList<Integer> rightBoundry=new ArrayList<>();
        Node cur= root.right;
        while (cur!=null) {
            if (!isLeaf(cur)) rightBoundry.add(cur.value); 
            if (cur.right!=null) cur=cur.right;
            else cur=cur.left;
        }
        for (int i =rightBoundry.size()-1; i >= 0 ; i--) {
            list.add(rightBoundry.get(i));
        }
    }
    private static void addLeaf(Node root,ArrayList <Integer> list){
        if(isLeaf(root)) list.add(root.value);
        if(root.left!=null) addLeaf(root.left, list);
        if(root.right!=null) addLeaf(root.right, list);
    }

    /**
     * Pair
     */ 
    public static class Pair {
        Node node;
        int hd;
        Pair(Node root, int hd){
            this.node=root;
            this.hd=hd;
        };
    }
    //Top view of binary tree
    public static ArrayList<Integer> topView(Node root){
        ArrayList<Integer> ans=new ArrayList<>();
        if (root==null) {
            return ans;
        }

        Map<Integer, Integer> map=new TreeMap<>();
        Queue<Pair> q=new LinkedList<Pair>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair pair=q.poll();
            int hd= pair.hd;
            Node temp= pair.node;

            if (map.get(hd)==null) {
                map.put(hd,temp.value);
            }

            if (temp.left!=null) {
                q.add(new Pair(temp.left, hd-1));
            }

            if (temp.right!=null) {
                q.add(new Pair(temp.right, hd+1));
            }

        }
        for(Map.Entry<Integer, Integer> entry :map.entrySet()){
            ans.add(entry.getValue());
        }
        return ans;
    }

    //Bottom view of binary tree
    public static ArrayList<Integer> bottomView(Node root){
        ArrayList<Integer> ans=new ArrayList<>();
        if (root==null) {
            return ans;
        }

        Map<Integer, Integer> map=new TreeMap<>();
        Queue<Pair> q= new LinkedList<Pair>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair pair=q.poll();
            int hd= pair.hd;
            Node temp= pair.node;
            map.put(hd,temp.value);

            
            if (temp.left!=null) {
                q.add(new Pair(temp.left, hd-1));
            }

            if (temp.right!=null) {
                q.add(new Pair(temp.right, hd+1));
            }

            
        }
        for(Map.Entry<Integer, Integer> entry :map.entrySet()){
            ans.add(entry.getValue());
        }
        return ans;
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

        Node root2 = new Node(10);
        root2.left = new Node(5);
        root2.right = new Node(-3);
        root2.left.left = new Node(3);
        root2.left.right = new Node(2);
        root2.right.right = new Node(11);
        root2.left.left.left = new Node(3);
        root2.left.left.right = new Node(-2);
        root2.left.right.right = new Node(1);

        // Performing iterative preorder traversal
        ArrayList<Integer> answer= bottomView(root);


        // Printing the result
        System.out.println(answer);
    }
}
