package tech.anaxka.common.utility.data;

import java.io.Serializable;

public interface Identifiable<ID extends Comparable<ID> & Serializable> {
    ID getIdentifier();
}
