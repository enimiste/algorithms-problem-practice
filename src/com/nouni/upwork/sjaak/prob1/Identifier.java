package com.nouni.upwork.sjaak.prob1;

import java.util.Objects;

public interface Identifier {
    static Identifier fromString(String id) {
        return new IdentifierImpl(id);
    }

    /**
     * Implementation
     */
    class IdentifierImpl implements Identifier {
        private final String id;

        public IdentifierImpl(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IdentifierImpl that = (IdentifierImpl) o;
            return (id == null && that.id == null) || id.compareTo(that.id) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
