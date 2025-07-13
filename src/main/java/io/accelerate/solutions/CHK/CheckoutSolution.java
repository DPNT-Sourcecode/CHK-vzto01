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
        if(skus.length()==0){return 0;}
        int total = 0;
        int aCount = 0;
        int bCount = 0;
        try {
             for(Character c : skus.toCharArray()) {
            if (c == 'A'){
                aCount += 1;
            }else if (c == 'B') {
                bCount += 1;
            } 
            else {
                total += prices.get(c);
            }
        }
        total += ((130 * Math.floorDiv(aCount, 3)) + ((aCount % 3) * 50)); //work out how many times we have 3 of A, times that by 130, and then add on any remaining A's
        total += ((45 * Math.floorDiv(bCount, 2)) + ((bCount % 2) * 30));
        return total;
        } catch (Exception e) {
            return -1;
        }
    }
}

