package com;

public class TravelPlan {
    public static void main(String[] args) {
        System.out.println(travelPlan(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{1, 15, 30}, new int[]{2, 5, 4}, 7, 0));
        System.out.println(travelPlan(new int[]{1, 3, 6, 7, 8, 20}, new int[]{2, 3, 5}, new int[]{2, 7, 15}, 6, 0)); // wrong answer need to check
        System.out.println(travelPlan(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{1, 15, 30}, new int[]{2, 5, 4}, 7, 0, new Integer[8]));
    }

    // https://www.geeksforgeeks.org/minimize-cost-to-travel-using-given-travel-plans/

    public static int travelPlan(int[] days, int[] span, int[] cost, int n, int index, Integer[] cache) {
        if(index >= n)
            return 0;

        if(cache[index] != null)
            return cache[index];

        int cost1 = cost[0] + travelPlan(days, span, cost, n, index + span[0]);

        int i;
        for(i = index; i < n && days[i] < days[index] + span[1]; i++);

        int cost2 = cost[1] + travelPlan(days, span, cost, n, i);

        for(i = index; i < n && days[i] < days[index] + span[2]; i++);

        int cost3 = cost[2] + travelPlan(days, span, cost, n, i);

        return cache[index] = Math.min(cost1, Math.min(cost2, cost3));
    }


    public static int travelPlan(int[] days, int[] span, int[] cost, int n, int index) {
        if(index >= n)
            return 0;

        int cost1 = cost[0] + travelPlan(days, span, cost, n, index + span[0]);

        int i;
        for(i = index; i < n && days[i] < days[index] + span[1]; i++);

        int cost2 = cost[1] + travelPlan(days, span, cost, n, i);

        for(i = index; i < n && days[i] < days[index] + span[2]; i++);

        int cost3 = cost[2] + travelPlan(days, span, cost, n, i);

        return Math.min(cost1, Math.min(cost2, cost3));
    }
}
