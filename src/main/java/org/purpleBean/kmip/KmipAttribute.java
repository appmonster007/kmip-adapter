package org.purpleBean.kmip;

import org.purpleBean.kmip.common.enumeration.State;

public interface KmipAttribute extends KmipDataType {
    boolean isAlwaysPresent();

    boolean isServerInitializable();

    boolean isClientInitializable();

    boolean isServerModifiable(State state);

    boolean isClientModifiable(State state);

    boolean isClientDeletable();

    boolean isMultiInstanceAllowed();
}
