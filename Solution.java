import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String[] triples = sc.nextLine().split(" ");

            Map<Character, Integer> nodeIndex = new HashMap<>();
            Map<Character, List<Character>> tree = new HashMap<>();
            Set<Character> children = new HashSet<>();
            for (String triple : triples) {
                char parent = triple.charAt(0);
                char left = triple.charAt(1);
                char right = triple.charAt(2);

                tree.put(parent, Arrays.asList(left, right));
                children.add(left);
                children.add(right);
            }
            char root = ' ';
            for (char key : tree.keySet()) {
                if (!children.contains(key)) {
                    root = key;
                    break;
                }
            }
            nodeIndex.put(root, 0);
            Queue<Character> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                char parent = queue.poll();
                int parentIndex = nodeIndex.get(parent);
                List<Character> childrenList = tree.get(parent);

                if (childrenList != null) {
                    char left = childrenList.get(0);
                    char right = childrenList.get(1);

                    if (left != '-') {
                        nodeIndex.put(left, 2 * parentIndex + 1);
                        queue.add(left);
                    }
                    if (right != '-') {
                        nodeIndex.put(right, 2 * parentIndex + 2);
                        queue.add(right);
                    }
                }
            }
            System.out.println(nodeIndex.get('A'));
        }
        sc.close();
    }
}
