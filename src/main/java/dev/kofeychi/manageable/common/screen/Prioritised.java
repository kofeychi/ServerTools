package dev.kofeychi.manageable.common.screen;

public class Prioritised<T> {
    public T value;
    public long Priority = 0;
    public Prioritised(T value) {
        this.value = value;
    }
}
