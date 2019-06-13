package com.xcc.comm.util;

import java.util.ArrayList;
import java.util.List;

public class MethodUtil {


    /**
     * 按余数分组
     * @param list
     * @param groupCount
     * @return
     */
    private static List<List<Integer>> groupListByRemainder(List<Integer> list, Integer groupCount) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) {
            //  按照余数来分组
            List<Integer> tempList = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (((Integer) (j % groupCount)).equals(i)) {
                    tempList.add(list.get(j));
                }
            }
            if (tempList.size() != 0) {
                lists.add(tempList);
            }
        }
        return lists;
    }
}
