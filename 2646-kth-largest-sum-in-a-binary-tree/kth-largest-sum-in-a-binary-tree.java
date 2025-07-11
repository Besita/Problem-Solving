/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    long bruteforce(TreeNode root,int k){
        TreeNode curr=root;
        int level=1;
        Queue<TreeNode> q=new LinkedList<>();
        PriorityQueue<Long> maxheap=new PriorityQueue<>(Collections.reverseOrder());
        q.add(curr);
        while(!q.isEmpty()){
            int levelsize=q.size();
            long levelsum=0;
            for(int i=0;i<levelsize;i++){
                TreeNode node=q.poll();
                levelsum+=node.val;
                //q.pop();
                if(node.left!=null)
                    q.add(node.left);
                
                if(node.right!=null)
                    q.add(node.right);      
            }
            maxheap.offer(levelsum);
        }
        int itr=1;
        while(!maxheap.isEmpty()){
            long ans= maxheap.poll();
            if(itr==k){                
                return ans;
            }
            itr++;
        }
        return -1;
    }
    long optimized(TreeNode root,int k){
        TreeNode curr=root;
        int level=1;
        Queue<TreeNode> q=new LinkedList<>();
        PriorityQueue<Long> minheap=new PriorityQueue<>();
        q.add(curr);
        while(!q.isEmpty()){
            int levelsize=q.size();
            long levelsum=0;
            for(int i=0;i<levelsize;i++){
                TreeNode node=q.poll();
                levelsum+=node.val;
                //q.pop();
                if(node.left!=null)
                    q.add(node.left);
                
                if(node.right!=null)
                    q.add(node.right);      
            }
            minheap.offer(levelsum);
            if(minheap.size()>k)
                minheap.poll();
        }
        if(minheap.size()==k)
            return minheap.poll();
        return -1;
    }
    public long kthLargestLevelSum(TreeNode root, int k) {
        //return bruteforce(root,k);  //using maxheap TC:O(n(queue)+mlogm(heap)) SC:O(m+n)
        return optimized(root,k);  //using minheap
    }
}
