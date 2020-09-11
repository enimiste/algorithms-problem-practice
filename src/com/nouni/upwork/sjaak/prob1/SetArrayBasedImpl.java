package com.nouni.upwork.sjaak.prob1;

import java.util.Arrays;
import java.util.Collection;

/**
 * This implementation uses the String.compare function to check for equality
 * Otherwise equals for string checks object references equality
 */
public class SetArrayBasedImpl implements Set {

    protected Identifier[] data;//Data Container
    protected int currentPosition;
    protected int maxElements;

    public SetArrayBasedImpl(int maxElements) {
        if (maxElements < 0)
            throw new IllegalArgumentException("Max elements should be positive");
        data = new Identifier[maxElements];
        currentPosition = 0;
        this.maxElements = maxElements;
    }

    @Override
    public void add(Identifier id) {
        if (id == null)
            return;//ignore it, we can also throw an exception
        if (!contains(id) && currentPosition < maxElements)
            data[currentPosition++] = id;
    }

    @Override
    public boolean contains(Identifier id) {
        for (int i = 0; i < currentPosition; i++) {
            Identifier v = data[i];
            if (v != null && v.equals(id))
                return true;
        }
        return false;
    }

    /**
     * @param set2
     * @return
     */
    @Override
    public Set diff(Set set2) {
        SetArrayBasedImpl res = new SetArrayBasedImpl(maxElements);
        for (int i = 0; i < currentPosition; i++) {
            Identifier v = data[i];
            if (!set2.contains(v))
                res.add(v);
        }
        return res;
    }

    /**
     * @param set2
     * @return
     */
    @Override
    public Set intersection(Set set2) {
        SetArrayBasedImpl res = new SetArrayBasedImpl(maxElements);
        for (int i = 0; i < currentPosition; i++) {
            Identifier v = data[i];
            if (set2.contains(v))
                res.add(v);
        }
        for (Identifier s : set2.getElements())
            if (contains(s))
                res.add(s);
        return res;
    }

    /**
     * @param set2
     * @return
     */
    @Override
    public Set union(Set set2) {
        SetArrayBasedImpl res = new SetArrayBasedImpl(maxElements);
        for (int i = 0; i < currentPosition; i++)
            res.add(data[i]);
        for (Identifier s : set2.getElements())
            res.add(s);
        return res;
    }

    /**
     * @param set2
     * @return
     */
    @Override
    public Set symDiff(Set set2) {
        SetArrayBasedImpl res = new SetArrayBasedImpl(maxElements);
        for (int i = 0; i < currentPosition; i++) {
            Identifier v = data[i];
            if (!set2.contains(v))
                res.add(v);
        }
        for (Identifier s : set2.getElements())
            if (!contains(s))
                res.add(s);
        return res;
    }

    @Override
    public Collection<Identifier> getElements() {
        return Arrays.asList(Arrays.copyOfRange(data, 0, currentPosition));
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("{");
        for (int i = 0; i < currentPosition; i++)
            sb.append(data[i]).append(" ");
        sb.append('}');
        return sb.toString();
    }

}
