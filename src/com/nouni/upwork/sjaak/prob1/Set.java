package com.nouni.upwork.sjaak.prob1;

import java.util.Collection;

public interface Set {
    void add(Identifier id);

    boolean contains(Identifier id);

    Set diff(Set set2);

    Set intersection(Set set2);

    Set union(Set set2);

    Set symDiff(Set set2);

    Collection<Identifier> getElements();
}
