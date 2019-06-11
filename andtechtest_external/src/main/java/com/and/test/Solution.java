package com.and.test;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Piotr Zadka
 */

public class Solution {

    static ArrayList<Character> inputArr = new ArrayList<>();
    static ArrayList<String> resultArr = new ArrayList<>();

    /**
     * The following is the method where the solution shall be written
     */

    public static String solution(String input) throws NumberFormatException {
        String output;

        // Check if input contains any number if not throw exception
        if(input.matches(".*\\d.*")){

            // Loop through input string and check each character
            for(int i = 0; i < input.length();i++){
                // If character is a number add it to array
                if(Character.isDigit(input.charAt(i))){
                    inputArr.add(input.charAt(i));
                }
            }
            // Check for permutations
            combinations(inputArr, 0);

            // Reverse array in ascending order
            Collections.sort(resultArr,Collections.reverseOrder());

            // Build string output using elements from array
            output = String.join(", ", resultArr);

            // Clear arrays for new input
            inputArr.clear();
            resultArr.clear();

        }else{
            throw new NumberFormatException("Input needs to include numbers");
        }
        return output;
    }

    /**
     * Following method is calling itself till all available choices for combination have been made.
     * Every time it calls itself a new array with swapped values and new starting pointer is supplied
     */
    private static void combinations(ArrayList<Character> arr, int start) {

        // String that stores combined values for each finished iteration of permutation loop.
        String combi = "";

        for(int i = start; i < arr.size(); i++){
            // sSwap places of starting index and next index in array till it equals arr.size() - 1 (for size 3 its 2)
            // Initially indexes swap with themselves till next iteration occurs.
            Collections.swap(arr,i,start);

            // Call method itself to move to next index and start iteration again.
            combinations(arr,start+1);

            // Revert array back in case an option is missed.
            Collections.swap(arr,i,start);
        }

        // if pointer = array size - 1 (There is nothing else to swap in array) meaning we have reached permutation descendant
        // add combination to results array
        if(start == arr.size()-1){
            for(int k = 0; k < arr.size(); k++){
                combi = combi + arr.get(k).charValue();
            }
            resultArr.add(combi);
        }
    }

    public static void main(String args[]) {

        // Example input with numbers only
        System.out.println("Result: "+solution("236"));

        // Example input with letters and numbers
        System.out.println("Result: "+solution("A 3B2 C6D"));

        // Example input with letters only
        // This should throw exception error
        System.out.println("Result: "+solution("ABC"));
    }

}
