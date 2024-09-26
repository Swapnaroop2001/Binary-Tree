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
     * TreeNode
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    // Iterative Preorder Traversal
    public static List<Integer> IterativePreorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            root = st.pop();
            list.add(root.val);
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
    public static List<Integer> IterativeInorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> st = new Stack<>();
        TreeNode TreeNode = root;

        while (true) {
            if (TreeNode != null) {
                st.push(TreeNode);
                TreeNode = TreeNode.left;
            } else {
                if (st.isEmpty()) {
                    break;
                }
                TreeNode = st.pop();
                list.add(TreeNode.val);
                TreeNode = TreeNode.right;
            }
        }
        return list;
    }

    // Iterative Postorder Traversal
    public static List<Integer> IterativePostorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
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
            list.add(st2.pop().val);
        }
        return list;
    }

    // Height or Maximu Depth of tree
    public static int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = findHeight(root.left);
        int rh = findHeight(root.right);
        return 1 + Math.max(lh, rh);
    }

    // Check if tree is balanced
    public static boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    public static int dfsHeight(TreeNode root) {
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
    public static int findDiameter(TreeNode root) {
        int[] Diameter = new int[1];
        findHeight(root, Diameter);
        return Diameter[0];
    }

    public static int findHeight(TreeNode root, int[] Diameter) {
        if (root == null) {
            return 0;
        }
        int lh = findHeight(root.left, Diameter);
        int rh = findHeight(root.right, Diameter);

        Diameter[0] = Math.max(Diameter[0], lh + rh);
        return Math.max(lh, rh) + 1;
    }

    // Maximum sum path
    public static int maxPathSum(TreeNode root) {
        int maxValue[] = new int[1];
        maxValue[0] = Integer.MIN_VALUE;
        maxPathDown(root, maxValue);
        return maxValue[0];
    }

    public static int maxPathDown(TreeNode root, int[] maxValue) {
        if (root == null) {
            return 0;
        }
        int lsum = Math.max(0, maxPathDown(root.left, maxValue));
        int Rsum = Math.max(0, maxPathDown(root.right, maxValue));
        maxValue[0] = Math.max(maxValue[0], root.val + lsum + Rsum);
        return root.val + Math.max(lsum, Rsum);
    }

    // Check if trees are same
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return p == q;
        }
        return (p.val) == (q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 103. Binary Tree Zigzag Level Order Traversal
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        boolean LeftToRight = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode TreeNode = queue.poll();
                if (LeftToRight) {
                    level.addLast(TreeNode.val);
                } else {
                    level.addFirst(TreeNode.val);
                }

                if (TreeNode.left != null) {
                    queue.offer(TreeNode.left);
                }
                if (TreeNode.right != null) {
                    queue.offer(TreeNode.right);
                }
            }
            result.add(level);
            LeftToRight = !LeftToRight;
        }
        return result;
    }

    // Boundry Traversal
    public static ArrayList<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        if (!isLeaf(root)) {
            list.add(root.val);
        }
        addLeftBoundry(root, list);
        addLeaf(root, list);
        addRightBoundry(root, list);
        return list;
    }

    private static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    private static void addLeftBoundry(TreeNode root, ArrayList<Integer> list) {
        TreeNode cur = root.left;
        while (cur != null) {
            if (!isLeaf(cur))
                list.add(cur.val);
            if (cur.left != null)
                cur = cur.left;
            else
                cur = cur.right;
        }
    }

    private static void addRightBoundry(TreeNode root, ArrayList<Integer> list) {
        ArrayList<Integer> rightBoundry = new ArrayList<>();
        TreeNode cur = root.right;
        while (cur != null) {
            if (!isLeaf(cur))
                rightBoundry.add(cur.val);
            if (cur.right != null)
                cur = cur.right;
            else
                cur = cur.left;
        }
        for (int i = rightBoundry.size() - 1; i >= 0; i--) {
            list.add(rightBoundry.get(i));
        }
    }

    private static void addLeaf(TreeNode root, ArrayList<Integer> list) {
        if (isLeaf(root))
            list.add(root.val);
        if (root.left != null)
            addLeaf(root.left, list);
        if (root.right != null)
            addLeaf(root.right, list);
    }

    /**
     * Pair
     */
    public static class Pair {
        TreeNode TreeNode;
        int hd;

        Pair(TreeNode root, int hd) {
            this.TreeNode = root;
            this.hd = hd;
        };
    }

    // Top view of binary tree
    public static ArrayList<Integer> topView(TreeNode root) {
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
            TreeNode temp = pair.TreeNode;

            if (map.get(hd) == null) {
                map.put(hd, temp.val);
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
    public static ArrayList<Integer> bottomView(TreeNode root) {
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
            TreeNode temp = pair.TreeNode;
            map.put(hd, temp.val);

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
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    public static void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.val);
        }
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    // Left View of Binary tree
    public static List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        leftView(root, result, 0);
        return result;
    }

    public static void leftView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.val);
        }
        leftView(curr.left, result, currDepth + 1);
        leftView(curr.right, result, currDepth + 1);
    }

    // Symmetric tree
    public static boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    private static boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return right == left;
        }
        if (left.val != right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    // Print root to TreeNode Path
    public static ArrayList<Integer> solve(TreeNode root, int B) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        getPath(root, result, B);
        return result;
    }

    private static boolean getPath(TreeNode root, ArrayList<Integer> arr, int x) {
        if (root == null) {
            return false;
        }
        arr.add(root.val);
        if (root.val == x) {
            return true;
        }
        if (getPath(root.left, arr, x) || getPath(root.right, arr, x)) {
            return true;
        }

        arr.remove(arr.size() - 1);
        return false;
    }

    // Lowest Common Ancestor
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    // Children Sum Property in Binary Tree | O(N) Approach
    public static void changeTree(TreeNode root) {
        if (root == null)
            return;
        int child = 0;
        if (root.left != null) {
            child += root.left.val;
        }
        if (root.right != null) {
            child += root.right.val;
        }
        if (child >= root.val) {
            root.val = child;
        } else {
            if (root.left != null) {
                root.left.val = root.val;
            } else if (root.right != null)
                root.right.val = root.val;
        }
        changeTree(root.left);
        changeTree(root.right);

        int total = 0;
        if (root.left != null)
            total = root.left.val;
        if (root.right != null)
            total = root.right.val;
        if (root.left != null || root.right != null)
            root.val = total;
    }

    // Maximum width of the binary tree
    public static int maxWidth(TreeNode root) {
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

    public static int getWidth(TreeNode TreeNode, int level) {
        if (TreeNode == null)
            return 0;

        if (level == 1)
            return 1;
        else if (level > 1)
            return getWidth(TreeNode.left, level - 1)
                    + getWidth(TreeNode.right, level - 1);
        return 0;
    }

    // Print all TreeNodes at a distance k from given TreeNode
    List<TreeNode> path = null;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        path = new ArrayList<>();
        findPath(root, target);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            findKDistanceFromTreeNode(
                    path.get(i), K - i, result, i == 0 ? null : path.get(i - 1));
        }
        return result;
    }

    public void findKDistanceFromTreeNode(TreeNode TreeNode, int dist, List<Integer> result, TreeNode blocker) {
        if (dist < 0 || TreeNode == null || (blocker != null && TreeNode == blocker)) {
            return;
        }

        if (dist == 0) {
            result.add(TreeNode.val);
        }

        findKDistanceFromTreeNode(TreeNode.left, dist - 1, result,
                blocker);
        findKDistanceFromTreeNode(TreeNode.right, dist - 1, result,
                blocker);
    }

    private boolean findPath(TreeNode TreeNode, TreeNode target) {
        if (TreeNode == null)
            return false;

        if (TreeNode == target || findPath(TreeNode.left, target)
                || findPath(TreeNode.right, target)) {
            path.add(TreeNode);
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
    public static String serialize(TreeNode root) {
        if (root == null)
            return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode TreeNode = q.poll();
            if (TreeNode == null) {
                res.append("n ");
                continue;
            }
            res.append(TreeNode.val + " ");
            q.add(TreeNode.left);
            q.add(TreeNode.right);
        }
        return res.toString();
    }

    public static TreeNode deSerialize(String data) {
        if (data.equals(" "))
            return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] vals = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        q.add(root);
        for (int i = 1; i < vals.length; i++) {
            TreeNode parent = q.poll();
            if (!vals[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(vals[i]));
                parent.left = left;
                q.add(left);
            }
            if (++i < vals.length && !vals[i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(vals[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }

    // Search in a Binary Search Tree
    public static TreeNode searchBST(TreeNode root, int target) {
        while (root != null && root.val != target) {
            if (root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }

    // Ceil in a Binary Search Tree
    public static int findCeil(TreeNode root, int key) {
        int ceil = -1;

        while (root != null) {
            if (root.val == key) {
                return root.val;
            }

            if (root.val > key) {
                ceil = root.val;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return ceil;
    }

    // Floor in a Binary Search Tree
    public static int findFloor(TreeNode root, int key) {
        int floor = -1;
        while (root != null) {
            if (root.val == key) {
                return root.val;
            }
            if (root.val < key) {
                floor = root.val;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return floor;
    }

    // Insert a TreeNode in Binary tree
    public static TreeNode insertTreeNode(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode curr = root;
        while (true) {
            if (curr.val <= val) {
                if (curr.right!=null) {
                    curr=curr.right;
                } else {
                    curr.right= new TreeNode(val);
                    break;
                }
            } else {
                if (curr.left!=null) {
                    curr=curr.left;
                } else {
                    curr.left= new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }

    //Delete a TreeNode from binary tree
    public static TreeNode deleteTreeNode(TreeNode root, int key ){
        if (root==null) {
            return null;
        }

        if (key < root.val) {
            root.left=deleteTreeNode(root.left, key);
        }else if(key> root.val){
            root.right=deleteTreeNode(root.right, key);
        }
        else{
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.val=findMin(root.right);
            root.right = deleteTreeNode(root.right, root.val);
        }
        return root;
    }
    private static int findMin(TreeNode root){
        while (root.left!=null) {
            root=root.left;
        }
        return root.val;
    }

    //Validate BST
    public static boolean isBST(TreeNode root) {
        return isBSTUtil(root, Long.MIN_VALUE, Integer.MAX_VALUE);
    }    
    private static boolean isBSTUtil(TreeNode root,long min,long max){
        if (root==null) {
            return true;
        }
        if (root.val<= min || root.val>=max) {
            return false;
        }
        return isBSTUtil(root.left, min, root.val) && isBSTUtil(root.right, root.val, max );
    }

    //LCA in BST
    public static TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root== null){
            return null;
        }
        TreeNode curr= root;
        if (curr.val< p.val && curr.val< q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (curr.val> p.val && curr.val> q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }

    // Kth smallet selement in BST
    static int count=0;
    static int result=0;

    public static int kthSmallest(TreeNode root, int k) {
        inordertraversal(root, k);
        return result;
    }
    public static  void inordertraversal(TreeNode root,int k ){
        if(root==null){
            return;
        }
        inordertraversal(root.left, k );
        count++;
        if (count==k){
            result= root.val;
            return;
        }
        inordertraversal(root.right, k );
    }

    //Inorder Successor in BST
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p){
        TreeNode successor= null;
        while(root!=null){
            if (p.val>=root.val) {
                root=root.right;
            }
            else{
                successor=root;
                root=root.left;
            }
        }
        return successor;
    }
    

    public static void main(String[] args) {
        // Creating a BST of height 2
        //         10
        //        /  \
        //       5    15
        //      / \   / \
        //     3   7 12  20

        TreeNode root = new TreeNode(10); // Root TreeNode
        root.left = new TreeNode(5);   // Left child of root
        root.right = new TreeNode(15);  // Right child of root
        
        root.left.left = new TreeNode(3);  // Left child of TreeNode 5
        root.left.right = new TreeNode(7);  // Right child of TreeNode 5
        
        root.right.left = new TreeNode(12); // Left child of TreeNode 15
        root.right.right = new TreeNode(22); // Right child of TreeNode 15
        
        TreeNode k= new TreeNode(3);
        TreeNode answer= inorderSuccessor(root, k);
        // Printing the result
        System.out.println(answer.val);
    }
}
