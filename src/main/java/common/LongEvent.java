package common;

public class LongEvent
{

    public LongEvent() {
        System.out.println("构建longevent...");
    }

    private long value;

    public void set(long value)
    {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}