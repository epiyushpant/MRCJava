public interface Event {
    Long getTimeStamp();
    void process();
}
