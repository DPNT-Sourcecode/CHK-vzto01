package io.accelerate.solutions.CHK;

import java.util.HashMap;

import io.accelerate.runner.SolutionNotImplementedException;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<Character, Integer> prices = new HashMap<Character, Integer>();
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);
        prices.put('E', 40);

        HashMap<Character, Integer> items = new HashMap<Character, Integer>();

        if (skus.length() == 0) {
            return 0;
        }

        try {
            for (Character c : skus.toCharArray()) {
                items.put(c, items.getOrDefault(items, 0) + 1);
            }
        } catch (Exception e) {
            return -1;
        }
        int total = 0;
        if (items.get('A')!=null && items.get('A') > 0) {
            int deal5 = Math.floorDiv(items.get('A'), 5);
            total += (deal5 * 200);
            items.put('A', (items.get('A') - (deal5 * 5)));

            int deal3 = Math.floorDiv(items.get('A'), 3);
            total += (deal3 * 130);
            items.put('A', (items.get('A') - (deal3 * 3)));
        }

        if (items.get('E') !=null && items.get('E') > 0) {
            int dealE = Math.floorDiv(items.get('E'), 2);
            items.put('B', (Math.max((items.get('B') - dealE), 0)));
        }

        if (
            items.get('B') !=null && items.get('B') > 0) {
            int dealB = Math.floorDiv(items.get('B'), 2);
            total += (dealB * 45);
            items.put('B', (items.get('B') - (dealB * 2)));
        }

        for (Character c : skus.toCharArray()) {
            total += prices.getOrDefault(c, 0);
        }
        return total;
    }
}


