package hackerEarth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SearchEngine {

    static class Node{
        int words;
        int prefixes;
        int maxWeight;
        Node[] edges = new Node[26];
    }
    
    static void initializeNode(Node node, int weight){
        node.words = 0;
        node.prefixes = 0;
        node.maxWeight = weight;
        for(int i=0;i<26;i++){
            node.edges[i] = null;
        }
    }
    
    static void addWord(Node node, String word, int weight){
        node.maxWeight = Math.max(node.maxWeight, weight);
        if(word.isEmpty()){
            node.words++;
        }else{
            node.prefixes++;
            int index = word.charAt(0) - 97;
            if(node.edges[index] == null){
                node.edges[index] = new Node();
                initializeNode(node.edges[index],weight);
            }
            word = word.substring(1);
            addWord(node.edges[index], word,weight);
        }
    }
    
    static int countPrefixes(Node node, String prefix){
 
        if(prefix.isEmpty()){
            return node.maxWeight;
        }
        
        int count = -1;
        int index = prefix.charAt(0) - 97;
        if(node.edges[index] == null){
            return -1;
        }else{
            prefix = prefix.substring(1);
            count = countPrefixes(node.edges[index], prefix);
        }
        return count;
    }
    
    public static void main(String[] args) throws Exception {
        
        Node root = new Node();
        initializeNode(root,-1);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        String[] sp = br.readLine().split(" ");
        
        int n,q;
        n = Integer.parseInt(sp[0]);
        q = Integer.parseInt(sp[1]);
        
        for(int i=0;i<n;i++){
            sp = br.readLine().split(" ");
            addWord(root, sp[0], Integer.parseInt(sp[1]));
        }
        
        StringBuilder output = new StringBuilder();
        for(int i=0;i<q;i++){
            output.append(countPrefixes(root, br.readLine()));
            output.append("\n");
        }
        
        System.out.println(output);
    }

}
