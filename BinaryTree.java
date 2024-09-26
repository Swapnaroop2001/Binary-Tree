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

    // Check if trees are same
    public static boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) {
            return p == q;
        }
        return (p.value) == (q.value) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 103. Binary Tree Zigzag Level Order Traversal
    public static List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        boolean LeftToRight = true;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (LeftToRight) {
                    level.addLast(node.value);
                } else {
                    level.addFirst(node.value);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
            LeftToRight = !LeftToRight;
        }
        return result;
    }

    // Boundry Traversal
    public static ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
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

    private static void addLeftBoundry(Node root, ArrayList<Integer> list) {
        Node cur = root.left;
        while (cur != null) {
            if (!isLeaf(cur))
                list.add(cur.value);
            if (cur.left != null)
                cur = cur.left;
            else
                cur = cur.right;
        }
    }

    private static void addRightBoundry(Node root, ArrayList<Integer> list) {
        ArrayList<Integer> rightBoundry = new ArrayList<>();
        Node cur = root.right;
        while (cur != null) {
            if (!isLeaf(cur))
                rightBoundry.add(cur.value);
            if (cur.right != null)
                cur = cur.right;
            else
                cur = cur.left;
        }
        for (int i = rightBoundry.size() - 1; i >= 0; i--) {
            list.add(rightBoundry.get(i));
        }
    }

    private static void addLeaf(Node root, ArrayList<Integer> list) {
        if (isLeaf(root))
            list.add(root.value);
        if (root.left != null)
            addLeaf(root.left, list);
        if (root.right != null)
            addLeaf(root.right, list);
    }

    /**
     * Pair
     */
    public static class Pair {
        Node node;
        int hd;

        Pair(Node root, int hd) {
            this.node = root;
            this.hd = hd;
        };
    }

    // Top view of binary tree
    public static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int hd = pair.hd;
            Node temp = pair.node;

            if (map.get(hd) == null) {
                map.put(hd, temp.value);
            }

            if (temp.left != null) {
                q.add(new Pair(temp.left, hd - 1));
            }

            if (temp.right != null) {
                q.add(new Pair(temp.right, hd + 1));
            }

        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }

    // Bottom view of binary tree
    public static ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int hd = pair.hd;
            Node temp = pair.node;
            map.put(hd, temp.value);

            if (temp.left != null) {
                q.add(new Pair(temp.left, hd - 1));
            }

            if (temp.right != null) {
                q.add(new Pair(temp.right, hd + 1));
            }

        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }

    // Right View of Binary tree
    public static List<Integer> rightSideView(Node root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    public static void rightView(Node curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.value);
        }
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    // Left View of Binary tree
    public static List<Integer> leftSideView(Node root) {
        List<Integer> result = new ArrayList<>();
        leftView(root, result, 0);
        return result;
    }

    public static void leftView(Node curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.value);
        }
        leftView(curr.left, result, currDepth + 1);
        leftView(curr.right, result, currDepth + 1);
    }

    // Symmetric tree
    public static boolean isSymmetric(Node root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    private static boolean isSymmetricHelp(Node left, Node right) {
        if (left == null || right == null) {
            return right == left;
        }
        if (left.value != right.value)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    // Print root to node Path
    public static ArrayList<Integer> solve(Node root, int B) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        getPath(root, result, B);
        return result;
    }

    private static boolean getPath(Node root, ArrayList<Integer> arr, int x) {
        if (root == null) {
            return false;
        }
        arr.add(root.value);
        if (root.value == x) {
            return true;
        }
        if (getPath(root.left, arr, x) || getPath(root.right, arr, x)) {
            return true;
        }

        arr.remove(arr.size() - 1);
        return false;
    }

    // Lowest Common Ancestor
    public static Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    // Children Sum Property in Binary Tree | O(N) Approach
    public static void changeTree(Node root) {
        if (root == null)
            return;
        int child = 0;
        if (root.left != null) {
            child += root.left.value;
        }
        if (root.right != null) {
            child += root.right.value;
        }
        if (child >= root.value) {
            root.value = child;
        } else {
            if (root.left != null) {
                root.left.value = root.value;
            } else if (root.right != null)
                root.right.value = root.value;
        }
        changeTree(root.left);
        changeTree(root.right);

        int total = 0;
        if (root.left != null)
            total = root.left.value;
        if (root.right != null)
            total = root.right.value;
        if (root.left != null || root.right != null)
            root.value = total;
    }

    // Maximum width of the binary tree
    public static int maxWidth(Node root) {
        int maxWidth = 0;
        int width;
        int h = findHeight(root);
        for (int i = 0; i <= h; i++) {
            width = getWidth(root, i);
            if (width > maxWidth)
                maxWidth = width;
        }
        return maxWidth;
    }

    public static int getWidth(Node node, int level) {
        if (node == null)
            return 0;

        if (level == 1)
            return 1;
        else if (level > 1)
            return getWidth(node.left, level - 1)
                    + getWidth(node.right, level - 1);
        return 0;
    }

    // Print all nodes at a distance k from given node
    List<Node> path = null;

    public List<Integer> distanceK(Node root, Node target, int K) {
        path = new ArrayList<>();
        findPath(root, target);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            findKDistanceFromNode(
                    path.get(i), K - i, result, i == 0 ? null : path.get(i - 1));
        }
        return result;
    }

    public void findKDistanceFromNode(Node node, int dist, List<Integer> result, Node blocker) {
        if (dist < 0 || node == null || (blocker != null && node == blocker)) {
            return;
        }

        if (dist == 0) {
            result.add(node.value);
        }

        findKDistanceFromNode(node.left, dist - 1, result,
                blocker);
        findKDistanceFromNode(node.right, dist - 1, result,
                blocker);
    }

    private boolean findPath(Node node, Node target) {
        if (node == null)
            return false;

        if (node == target || findPath(node.left, target)
                || findPath(node.right, target)) {
            path.add(node);
            return true;
        }

        return false;
    }

    class Distance {
        int val;

        Distance(int d) {
            val = d;
        }
    }

    // Serialize Binary Tree
    public static String serialize(Node root) {
        if (root == null)
            return "";
        Queue<Node> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.value + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public static Node deSerialize(String data) {
        if (data.equals(" "))
            return null;
        Queue<Node> q = new LinkedList<>();
        String[] values = data.split(" ");
        Node root = new Node(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            Node parent = q.poll();
            if (!values[i].equals("n")) {
                Node left = new Node(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (++i < values.length && !values[i].equals("n")) {
                Node right = new Node(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }

    // Search in a Binary Search Tree
    public static Node searchBST(Node root, int target) {
        while (root != null && root.value != target) {
            if (root.value < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }

    // Ceil in a Binary Search Tree
    public static int findCeil(Node root, int key) {
        int ceil = -1;

        while (root != null) {
            if (root.value == key) {
                return root.value;
            }

            if (root.value > key) {
                ceil = root.value;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return ceil;
    }

    // Floor in a Binary Search Tree
    public static int findFloor(Node root, int key) {
        int floor = -1;
        while (root != null) {
            if (root.value == key) {
                return root.value;
            }
            if (root.value < key) {
                floor = root.value;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return floor;
    }

    // Insert a node in Binary tree
    public static Node insertNode(Node root, int val) {
        if (root == null) return new Node(val);
        Node curr = root;
        while (true) {
            if (curr.value <= val) {
                if (curr.right!=null) {
                    curr=curr.right;
                } else {
                    curr.right= new Node(val);
                    break;
                }
            } else {
                if (curr.left!=null) {
                    curr=curr.left;
                } else {
                    curr.left= new Node(val);
                    break;
                }
            }
        }
        return root;
    }

    //Delete a node from binary tree
    public static Node deleteNode(Node root, int key ){
        if (root==null) {
            return null;
        }

        if (key < root.value) {
            root.left=deleteNode(root.left, key);
        }else if(key> root.value){
            root.right=deleteNode(root.right, key);
        }
        else{
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value=findMin(root.right);
            root.right = deleteNode(root.right, root.value);
        }
        return root;
    }
    private static int findMin(Node root){
        while (root.left!=null) {
            root=root.left;
        }
        return root.value;
    }

    //Validate BST
    public static boolean isBST(Node root) {
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }    
    private static boolean isBSTUtil(Node root,int min,int max){
        if (root==null) {
            return true;
        }
        if (root.value<= min || root.value>=max) {
            return false;
        }
        return isBSTUtil(root.left, min, root.value) && isBSTUtil(root.right, root.value, max );
    }

    

    public static void main(String[] args) {
        // Creating a BST of height 2
        //         10
        //        /  \
        //       5    15
        //      / \   / \
        //     3   7 12  20

        Node root = new Node(10); // Root node
        root.left = new Node(5);   // Left child of root
        root.right = new Node(15);  // Right child of root
        
        root.left.left = new Node(3);  // Left child of node 5
        root.left.right = new Node(7);  // Right child of node 5
        
        root.right.left = new Node(12); // Left child of node 15
        root.right.right = new Node(22); // Right child of node 15
        

        boolean answer = isBST(root);
        // Printing the result
        System.out.println(answer);
    }
}
