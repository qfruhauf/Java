import java.util.*;

class Solution {

    public static void main(){
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        groupAnagrams(strs);
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<char[], List<String>> map = new HashMap<>();
        List<String> innerList;

        for(int i = 0; i<strs.length; ++i) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);

            innerList = map.get(chars);
            if(innerList == null) {
                innerList = new ArrayList<String>();
                innerList.add(strs[i]);
                map.put(chars, innerList);
            } else {
                innerList.add(strs[i]);
            }
        }

        List<List<String>> resultList = new ArrayList<List<String>>();
        for(List<String> list : map.values()) {
            resultList.add(list);
        }
        return resultList;
    }
}