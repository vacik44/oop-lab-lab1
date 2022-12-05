package oop.labs.lab1;

import java.util.Arrays;
import java.util.HashSet;

public class StringCalculator
{
    public int add(String numbers) throws IllegalArgumentException
    {
        if (numbers.length() == 0) return 0;

        var delimiters = new HashSet<>(Arrays.asList(',', '\n'));

        try
        {
            var i = 0;

            if (numbers.startsWith("//"))
                if (numbers.length() < 4 || numbers.charAt(3) != '\n') throw new IllegalArgumentException();
                else if (numbers.length() == 4) return 0;
                else
                {
                    delimiters.add(numbers.charAt(2));
                    i = 4;
                }

            var sum = 0;
            var border = i;
            var start = border;

            while (i < numbers.length())
            {
                while (i < numbers.length() && !delimiters.contains(numbers.charAt(i))) i++;

                if (i > start && i != numbers.length() - 1)
                {
                    sum += Integer.parseInt(numbers, border, i, 10);
                    i++;
                    border = i;
                }
                else
                {
                    throw new IndexOutOfBoundsException();
                }
            }

            return sum;
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException ex)
        {
            throw new IllegalArgumentException("Incorrect input format.");
        }
    }
}
