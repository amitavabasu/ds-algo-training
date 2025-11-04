package training.other;

import java.util.*;

/*
    # Duplicate File Contents

    Given a list of strings, `paths`, where each string describes a single file in the format `directory_path/file_name(content)`, return groups of file paths that have identical file contents. Each group should contain the full paths of files that share the same content, and the result can be returned in any order.

    Input Format:

    - Each string represents a file with its full path and content
    - For example, `"root/a/1.txt(abcd)"` indicates that there is a file named `"1.txt"` located in the directory `"root/a"` with the content `"abcd"`

    Output Format:

    - The output is a list of lists of strings, where each nested list represents a group of at least `2` files that have the same content
    - A file path is represented as a string in the format `"directory_path/file_name"`
    - Only groups with at least two files should be included in the output

    Example 1:
    Input: paths = [ "root/a/1.txt(abcd)",
                     "root/a/2.txt(efgh)",
                     "root/b/3.txt(abcd)",
                     "root/b/4.txt(efgh)" ]
    Output: [["root/a/1.txt", "root/b/3.txt"],
             ["root/a/2.txt", "root/b/4.txt"]]
    Explanation: We have two groups of files with identical contents:
    - Files with content "abcd": "root/a/1.txt" and "root/b/3.txt"
    - Files with content "efgh": "root/a/2.txt" and "root/b/4.txt"

    Example 2:
    Input: paths = [ "root/a/1.txt(abcd)",
                     "root/a/2.txt(efgh)",
                     "root/a/3.txt(abcd)",
                     "root/a/4.txt(efgh)" ]
    Output: [ ["root/a/1.txt", "root/a/3.txt"],
              ["root/a/2.txt", "root/a/4.txt"] ]
    Explanation: We have the same two groups as Example 1, just with different file paths.

    Example 3:
    Input: paths = [ "root/a/b/c/d/1.txt(abc)",
                     "root/a/b/c/d/2.txt(def)" ]
    Output: []
    Explanation: No two files have the same content, so no groups are formed.

    Constraints:

    - The number of files is at most `10^4`
    - Each file path string has length at most `100` and does not contain spaces or parentheses.
    - File contents contain only lowercase letters.
    - Each file must belong to at least one directory.
    - No two files can share the exact same path and name.
    - Directories are single-worded, lower-cased, and do not contain any special characters.
 */
public class DuplicateFileContents {

    public static List<List<String>> findDuplicate(List<String> list) {
        if (list == null || list.isEmpty()) return Collections.emptyList();
        Map<String, List<String>> map = new HashMap<>();
        for (String string : list) {
            String path = string.substring(0, string.indexOf('('));
            String content = string.substring(string.indexOf('(')+1, string.indexOf(')'));
//            System.out.println("path=" + path + "; content=" + content);
            List<String> pathList = map.getOrDefault(content, new ArrayList<>());
            pathList.add(path);
            map.put(content, pathList);
        }
        List<List<String>> result = new ArrayList<>();
        for (String key : map.keySet()) {
            List<String> pathList = map.get(key);
            if (pathList.size() > 1) {
                result.add(pathList);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<String>> stringsList = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(
                        "root/a/1.txt(abcd)",
                        "root/a/2.txt(efgh)",
                        "root/b/3.txt(abcd)",
                        "root/b/4.txt(efgh)")),
                new ArrayList<>(Arrays.asList(
                        "root/a/b/c/d/1.txt(abc)",
                        "root/a/b/c/d/2.txt(def)"))
        ));

        for (List<String> strings : stringsList) {
            List<List<String>> result = findDuplicate(strings);
            System.out.println(result);
        }

    }




}
