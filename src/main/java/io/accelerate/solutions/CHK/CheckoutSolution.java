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
        for(Character c : skus.toCharArray()) {
            total += prices.get(c);
        }
        return total;
    }
}


