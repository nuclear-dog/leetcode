package leetcode;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Mid {
    public int maxVowels(String s, int k) {
        String vowels = "aeiou";

        int count  = 0;
        for (int i = 0; i<k && i<s.length(); i++){
            if (vowels.indexOf(s.charAt(i)) != -1) count++;
        }
        int ans = count;
        for (int i = k; i<s.length();i++){
            if (vowels.indexOf(s.charAt(i-k)) != -1) count--;
            if (vowels.indexOf(s.charAt(i)) != -1) count++;
            ans = Math.min(ans, count);
        }
        return ans;
    }

    int ans = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        if (root == null) return 0;
        int[] map = new int[10];
        pseudoPalindromicPathsDFS(root, map);
        return ans;
    }

    private void pseudoPalindromicPathsDFS(TreeNode root, int[] map) {
        map[root.val]++;
        if (root.left == null && root.right == null){
            //map中最多有1个奇数
            int count = 0;
            int i = 0;
            for(; i<10; i++){
                if (map[i] % 2 == 1){
                    count++;
                }
            }
            if (count<=1) ans++;
        }
        else{
            if (root.left != null) pseudoPalindromicPathsDFS(root.left, map);
            if (root.right != null) pseudoPalindromicPathsDFS(root.right, map);
        }
        map[root.val]--;
    }
}
