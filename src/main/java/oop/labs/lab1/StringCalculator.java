package oop.labs.lab1;

public class StringCalculator
{
    public int add(String numbers) throws IllegalArgumentException
    {
        if (numbers.length() == 0) return 0;

        var i = 0;
        while (i < numbers.length() && numbers.charAt(i) != ',') i ++;

        if (i > 0 && i != numbers.length() - 1)
            try
            {
                var sum = Integer.parseInt(numbers, 0, i, 10);
                if (i < numbers.length()) sum += Integer.parseInt(numbers, i + 1, numbers.length(), 10);
                return sum;
            }
            catch (NumberFormatException | IndexOutOfBoundsException ignored) {}

        throw new IllegalArgumentException("Incorrect input format.");
    }
}
