package oop.labs.lab1;

public class StringCalculator
{
    public int add(String numbers) throws IllegalArgumentException
    {
        if (numbers.length() == 0) return 0;

        try
        {
            var i = 0;
            var sum = 0;
            var border = 0;

            while (i < numbers.length())
            {
                while (i < numbers.length() && numbers.charAt(i) != ',') i++;

                if (i > 0 && i != numbers.length() - 1)
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
        catch (IndexOutOfBoundsException | NumberFormatException ex)
        {
            throw new IllegalArgumentException("Incorrect input format.");
        }
    }
}
