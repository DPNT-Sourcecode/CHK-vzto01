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
        int total = -1;
        int aCount = 0;
        int bCount = 0;
        for(Character c : skus.toCharArray()) {
            if (c == 'A'){
                aCount ++;
            }else if (c == 'B') {
                bCount ++;
            } 
            else {
                total += prices.get(c);
            }
        }
        total += ((130 * Math.floorDiv(aCount, 3)) + ((aCount % 3) * 50)); //work out how many times we have 3 of A, times that by 130, and then add on any remaining A's
        total += ((45 * Math.floorDiv(bCount, 2)) + ((aCount % 3) * 30));
        return total;
    }
}



