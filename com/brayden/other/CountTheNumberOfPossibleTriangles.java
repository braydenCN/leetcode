package com.brayden.other;

public class CountTheNumberOfPossibleTriangles {

    public int countPossibleTriangles(int[] arr){
        int len = arr.length;
        int answer = 0;
        for (int i = 0; i < len; ++i){
            int k = i;
            for (int j = i; j < len; ++j){
                while (len > k && arr[i] + arr[j] > arr[k])
                    ++k;
                answer += k - j;
            }
        }
        return answer;
    }
}
