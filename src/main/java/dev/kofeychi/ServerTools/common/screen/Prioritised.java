package dev.kofeychi.ServerTools.common.screen;

import org.jetbrains.annotations.NotNull;

public class Prioritised<T> {
    public T value;
    public long Priority = 0;
    public Prioritised(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public long getPriority() {
        return Priority;
    }

    public void setPriority(long priority) {
        Priority = priority;
    }
}
