package com.tencent.lucasshi;

import java.util.*;

/**
 * Created by fzy on 17/5/21.
 */
public class P207_CourseSchedule {

    class Edge {
        int i;
        int j;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, Set<Integer>> fromMap = new HashMap<>();
        HashMap<Integer, Set<Integer>> toMap = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int start = prerequisites[i][0];
            int end = prerequisites[i][1];

            if (!fromMap.containsKey(start)) {
                fromMap.put(start, new HashSet<>());
            }
            fromMap.get(start).add(end);

            if (!toMap.containsKey(start)) {
                toMap.put(end, new HashSet<>());
            }

            toMap.get(end).add(start);
        }

        HashSet<Integer> selectCourses = new HashSet<>();
        while (selectCourses.size() < numCourses) {
            boolean match = false;
            int matchCourseIndex = -1;
            for (int i = 0; i < numCourses; i++) {
                // 说明这个是可以的
                if (!fromMap.containsKey(i) && !selectCourses.contains(i)) {
                    matchCourseIndex = i;
                    match = true;
                    break;
                }
            }

            // 没有找到
            if (!match) {
                return false;
            }

            selectCourses.add(matchCourseIndex);
            // 清楚两个表
            Set<Integer> set = toMap.get(matchCourseIndex);
            for (int val:set) {
                fromMap.get(val).remove(matchCourseIndex);
                if (fromMap.get(val).size() == 0) {
                    fromMap.remove(val);
                }
            }

            toMap.remove(matchCourseIndex);
        }

        return true;
    }
}
