package io.accelerate.solutions.CHK;

import java.io.EOFException;
import java.util.HashMap;
import java.util.regex.Pattern;

import io.accelerate.runner.SolutionNotImplementedException;

public class CheckoutSolution {

    static int xForY(char itemChar, HashMap<Character, Integer> items, int itemNo, int discountVal) {
        Integer itemVal = items.get(itemChar);
        if (itemVal != null && itemVal > 0) {
            int deal5 = Math.floorDiv(itemVal, itemNo);
            items.put('A', (itemVal - (deal5 * itemNo)));
            return (deal5 * discountVal);
        }
        return 0;
    }

    static int getOneFree(char itemChar, HashMap<Character, Integer> items, int itemNo, char freeItemChar) {
         Integer itemVal = items.get(itemChar);
         Integer freeItemVal = items.get(freeItemChar);
         if (itemVal != null && freeItemVal != null && itemVal > 0) {
            int dealE = Math.floorDiv(itemVal, itemNo);
            
            items.put('B', (Math.max((freeItemVal - dealE), 0)));
        }
        return 0;
    }

    public Integer checkout(String skus) {
        HashMap<Character, Integer> prices = new HashMap<Character, Integer>();
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);
        prices.put('E', 40);
        prices.put('F', 10);

        HashMap<Character, Integer> items = new HashMap<Character, Integer>();
        if (Pattern.compile("[^A-Z]+").matcher(skus).find() == true) {
            return -1;
        }
        ;

        if (skus.length() == 0) {
            return 0;
        }

        try {
            for (Character c : skus.toCharArray()) {
                items.put(c, items.getOrDefault(c, 0) + 1);
            }
        } catch (Exception e) {
            return -1;
        }
        int total = 0;

        total += xForY('A', items, 5, 200);
        total += xForY('A', items, 3, 130);
        total += xForY('A', items, 2, 45);
        total += xForY('H', items, 5, 45);
        total += xForY('H', items, 10, 80);
        total += xForY('K', items, 2, 150);
        total += xForY('P', items, 5, 200);
        total += xForY('Q', items, 35, 80);
        total += xForY('V', items, 2, 90);
        total += xForY('Q', items, 35, 80);
        total += xForY('V', items, 3, 130);

        // if E/2 >=1, add 1-B-free deal and remove a B from list
        if (items.get('E') != null && items.get('B') != null && items.get('E') > 0) {
            int dealE = Math.floorDiv(items.get('E'), 2);
            items.put('B', (Math.max((items.get('B') - dealE), 0)));
        }

        // if F/3 >=1, add 3-4-2 deal and remove an F from list
        if (items.get('F') != null && items.get('F') > 0) {
            int dealF = Math.floorDiv(items.get('F'), 3);
            items.put('F', (items.get('F') - dealF));
        }

        // tally up remaining items
        for (Character k : items.keySet()) {
            total += (items.get(k) * prices.getOrDefault(k, 0));
        }
        return total;
    }
}


