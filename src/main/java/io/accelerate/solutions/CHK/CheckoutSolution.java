package io.accelerate.solutions.CHK;

import java.io.EOFException;
import java.util.HashMap;
import java.util.regex.Pattern;

import io.accelerate.runner.SolutionNotImplementedException;

public class CheckoutSolution {

    static int xForY(char itemChar, HashMap<Character, Integer> items, int itemNo, int discountVal) {
        Integer itemVal = items.get(itemChar);
        if (itemVal != null && itemVal > 0) {
            int noOfDeals = Math.floorDiv(itemVal, itemNo);
            items.put(itemChar, (itemVal - (noOfDeals * itemNo)));
            return (noOfDeals * discountVal);
        }
        return 0;
    }

    static void getOneFree(char itemChar, HashMap<Character, Integer> items, int itemNo, char freeItemChar) {
        Integer itemVal = items.get(itemChar);
        Integer freeItemVal = items.get(freeItemChar);
        if (itemVal != null && freeItemVal != null && itemVal > 0) {
            if (itemChar == 'U') {
                System.out.println(itemChar);
            }
            int noOfDeals = Math.floorDiv(itemVal, itemNo);
            items.put(freeItemChar, (Math.max((freeItemVal - noOfDeals), 0)));
        }
    }

    public Integer checkout(String skus) {
        HashMap<Character, Integer> prices = new HashMap<Character, Integer>();
        // There's a better way of doing this, I'd like to be given a csv and then just
        // read that in but it'd take just as long to write the csv
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);
        prices.put('E', 40);
        prices.put('F', 10);
        prices.put('G', 20);
        prices.put('H', 10);
        prices.put('I', 35);
        prices.put('J', 60);
        prices.put('K', 80);
        prices.put('L', 90);
        prices.put('M', 15);
        prices.put('N', 40);
        prices.put('O', 10);
        prices.put('P', 50);
        prices.put('Q', 30);
        prices.put('R', 50);
        prices.put('S', 30);
        prices.put('T', 20);
        prices.put('U', 40);
        prices.put('V', 50);
        prices.put('W', 20);
        prices.put('X', 90);
        prices.put('Y', 10);
        prices.put('Z', 50);

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

        getOneFree('E', items, 2, 'B');
        getOneFree('F', items, 3, 'F');
        getOneFree('N', items, 3, 'M');
        getOneFree('R', items, 3, 'Q');
        getOneFree('U', items, 4, 'U');

        total += xForY('A', items, 5, 200);
        total += xForY('A', items, 3, 130);
        total += xForY('B', items, 2, 45);
        total += xForY('H', items, 10, 80);
        total += xForY('H', items, 5, 45);
        total += xForY('K', items, 2, 150);
        total += xForY('P', items, 5, 200);
        total += xForY('Q', items, 3, 80);
        total += xForY('V', items, 3, 130);
        total += xForY('V', items, 2, 90);

        // tally up remaining items
        for (Character k : items.keySet()) {
            total += (items.get(k) * prices.getOrDefault(k, 0));
        }
        return total;
    }
}







