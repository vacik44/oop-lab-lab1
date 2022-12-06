package oop.labs.lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class StringCalculator
{
    public int add(String numbers) throws IllegalArgumentException
    {
        if (numbers.length() == 0) return 0;

        var delimiters = new HashSet<>(Arrays.asList(",", "\n"));
        var rethrow = false;

        try
        {
            var i = 0;

            if (numbers.startsWith("//"))
            {
                if (numbers.charAt(2) == '[')
                {
                    while (i < numbers.length() && numbers.charAt(i) != ']') i++;
                    if (numbers.charAt(i + 1) != '\n') throw new IllegalArgumentException();

                    delimiters.add(numbers.substring(3, i));
                    i += 2;
                }
                else
                {
                    if (numbers.length() < 4 || numbers.charAt(3) != '\n') throw new IllegalArgumentException();

                    delimiters.add(numbers.substring(2, 3));
                    i = 4;
                }

                if (numbers.length() == i) return 0;
            }

            var sum = 0;
            var border = i;
            var start = border;
            var negatives = new ArrayList<Integer>();

            while (i < numbers.length())
            {
                var c = numbers.charAt(i);

                if (Character.isDigit(c) || c == '-')
                {
                    if (c == '-') i++;
                    while (i < numbers.length() && Character.isDigit(numbers.charAt(i))) i++;

                    var num = Integer.parseInt(numbers, border, i, 10);

                    if (num < 0) negatives.add(num);
                    if (negatives.isEmpty() && num <= 1000) sum += num;
                }
                else
                {
                    while (i < numbers.length() && (c = numbers.charAt(i)) != '-' && !Character.isDigit(c)) i++;

                    if (border == start || i == numbers.length()) throw new IndexOutOfBoundsException();
                    if (!delimiters.contains(numbers.substring(border, i))) throw new IllegalArgumentException();
                }

                border = i;
            }

            if (negatives.isEmpty()) return sum;

            rethrow = true;
            throw new IllegalArgumentException(String.format("Negatives not allowed (%s found).", negatives));
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException ex)
        {
            if (rethrow) throw ex;
            throw new IllegalArgumentException("Incorrect input format.");
        }
    }
}
