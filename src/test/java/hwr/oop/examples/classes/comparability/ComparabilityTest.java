package hwr.oop.examples.classes.comparability;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class ComparabilityTest {

    private Train first;
    private Train second;
    private Train third;

    @BeforeEach
    void setUp() {
        first = new Train(12);
        second = new Train(13);
        third = new Train(14);
    }

    @Test
    void compareTo_FirstDrivesFasterThanSecond_FirstIsGreaterThanSecond() {
        first.accelerateTo(100);
        second.accelerateTo(90);
        int compareToResult = first.compareTo(second);
        assertThat(compareToResult).isPositive().isNotZero();
    }

    @Test
    void compareTo_FirstDrivesFasterThanSecond_SecondIsLessThanFirst() {
        first.accelerateTo(100);
        second.accelerateTo(90);
        int compareToResult = second.compareTo(first);
        assertThat(compareToResult).isNegative().isNotZero();
    }

    @Test
    void railCarComparator_compare_FirstTrainShorter_FirstLessThanSecond() {
        Comparator<Train> comparator = new RailCarComparator();
        int result = comparator.compare(first, second);
        assertThat(result).isNegative().isNotZero();
    }

    @Test
    void railCarComparator_compare_FirstTrainShorter_SecondTrainGreaterThanFirst() {
        Comparator<Train> comparator = new RailCarComparator();
        int result = comparator.compare(second, first);
        assertThat(result).isPositive().isNotZero();
    }

    @Test
    void comparableTransitivitaet() {
        first.accelerateTo(100);
        second.accelerateTo(100);
        third.accelerateTo(100);
        int resultOne = second.compareTo(first);
        int resultTwo = second.compareTo(third);
        int resultFinal = first.compareTo(third);
        if (resultOne == resultTwo){
            assertThat(resultFinal).isZero();
        }
    }

    @Test
    void comparableTransitivitaetComparator() {
        Comparator<Train> comparator = new RailCarComparator();
        int resultOne = comparator.compare(second, first);
        int resultTwo = comparator.compare(second, third);
        int resultFinal = comparator.compare(first, third);
        if (resultOne == resultTwo){
            System.out.println("Ich bin drin");
            assertThat(resultFinal).isZero();
        }
    }
}
