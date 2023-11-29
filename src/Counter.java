// 207789140 Sondos Zoabi.
/**
 * class counter.
 */
public class Counter {
    private int count;

    /**
     * add number to current count.
     * @param number to add to the count variable.
     */
    void increase(int number) {
        this.count = count + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number to decrease it from count var.
     */
    void decrease(int number) {
        this.count = count - number;
    }

    /**
     * get current count.
     *
     * @return the value of count variable.
     */
    int getValue() {
        return count;
    }
}