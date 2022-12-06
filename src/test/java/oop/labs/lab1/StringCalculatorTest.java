package oop.labs.lab1;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class StringCalculatorTest
{
    private StringCalculator testCalculator;

    @Before
    public void prepare()
    {
        testCalculator = new StringCalculator();
    }

    @Test
    public void step_1_basic()
    {
        assertThat(testCalculator.add("")).isEqualTo(0);
        assertThat(testCalculator.add("1")).isEqualTo(1);
        assertThat(testCalculator.add("11")).isEqualTo(11);
        assertThat(testCalculator.add("1,2")).isEqualTo(3);
        assertThat(testCalculator.add("11,22")).isEqualTo(33);
    }

    @Test
    public void step_1_extended()
    {
        assertThat(catchThrowable(() -> testCalculator.add("@"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add(","))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add(",1"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("1,"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("1,,"))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void step_2()
    {
        assertThat(testCalculator.add("1,2,3,4")).isEqualTo(10);
        assertThat(testCalculator.add("11,22,33")).isEqualTo(66);
    }

    @Test
    public void step_3_basic()
    {
        assertThat(testCalculator.add("1\n2")).isEqualTo(3);
        assertThat(testCalculator.add("11\n22")).isEqualTo(33);
        assertThat(testCalculator.add("1\n2,3\n4")).isEqualTo(10);
        assertThat(testCalculator.add("11,22\n33")).isEqualTo(66);
        assertThat(testCalculator.add("11\n22\n33\n44")).isEqualTo(110);
    }

    @Test
    public void step_3_extended()
    {
        assertThat(catchThrowable(() -> testCalculator.add("\n"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("\n1"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("1\n"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("1,\n"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("1\n\n"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("1\n,"))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void step_4_basic()
    {
        assertThat(testCalculator.add("//;\n")).isEqualTo(0);
        assertThat(testCalculator.add("//;\n11")).isEqualTo(11);
        assertThat(testCalculator.add("//;\n1;2")).isEqualTo(3);
        assertThat(testCalculator.add("//,\n1,2")).isEqualTo(3);
        assertThat(testCalculator.add("//\n\n1\n2")).isEqualTo(3);
        assertThat(testCalculator.add("//$\n11$22")).isEqualTo(33);
        assertThat(testCalculator.add("//$\n1$2,3$4")).isEqualTo(10);
        assertThat(testCalculator.add("//%\n11\n22%33")).isEqualTo(66);
        assertThat(testCalculator.add("//%\n11%22%33%44")).isEqualTo(110);
    }

    @Test
    public void step_4_extended()
    {
        assertThat(catchThrowable(() -> testCalculator.add("//\n1"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("//;\n1*2"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("//;\n;1;2"))).isInstanceOf(IllegalArgumentException.class);
        assertThat(catchThrowable(() -> testCalculator.add("//;;\n1;2"))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void step_5_basic()
    {
        assertThat(catchThrowable(() -> testCalculator.add("-1,2,3"))).hasMessage(String.format("Negatives not allowed (%s found).", List.of(-1)));
        assertThat(catchThrowable(() -> testCalculator.add("1,-2,3"))).hasMessage(String.format("Negatives not allowed (%s found).", List.of(-2)));
        assertThat(catchThrowable(() -> testCalculator.add("1,-2,-3"))).hasMessage(String.format("Negatives not allowed (%s found).", Arrays.asList(-2, -3)));
        assertThat(catchThrowable(() -> testCalculator.add("-1,-2,-3"))).hasMessage(String.format("Negatives not allowed (%s found).", Arrays.asList(-1, -2, -3)));
        assertThat(catchThrowable(() -> testCalculator.add("-11,-22,-33"))).hasMessage(String.format("Negatives not allowed (%s found).", Arrays.asList(-11, -22, -33)));
    }

    @Test
    public void step_5_extended()
    {
        assertThat(catchThrowable(() -> testCalculator.add("//;\n-11;-22;-33"))).hasMessage(String.format("Negatives not allowed (%s found).", Arrays.asList(-11, -22, -33)));
        assertThat(catchThrowable(() -> testCalculator.add("//;\n11;-22\n33,-44"))).hasMessage(String.format("Negatives not allowed (%s found).", Arrays.asList(-22, -44)));
        assertThat(catchThrowable(() -> testCalculator.add("//;\n11;-22\n33,-44\n"))).hasMessage("Incorrect input format.");
    }

    @Test
    public void step_6()
    {
        assertThat(testCalculator.add("999,1000,1001")).isEqualTo(1999);
        assertThat(testCalculator.add("1001,1000,999")).isEqualTo(1999);
        assertThat(testCalculator.add("1001,999,20000")).isEqualTo(999);
    }
}
