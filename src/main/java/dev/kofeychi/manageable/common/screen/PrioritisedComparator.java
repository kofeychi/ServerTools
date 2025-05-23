package dev.kofeychi.manageable.common.screen;

import java.util.Comparator;

public class PrioritisedComparator implements Comparator<Prioritised> {
    @Override
    public int compare(Prioritised o1, Prioritised o2) {
        return Long.compare(o1.Priority, o2.Priority);
    }
}
