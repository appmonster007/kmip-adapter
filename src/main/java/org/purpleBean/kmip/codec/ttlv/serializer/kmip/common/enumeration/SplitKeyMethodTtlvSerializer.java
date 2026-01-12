package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodTtlvSerializer extends AbstractKmipTtlvSerializer<SplitKeyMethod, Integer> {

    public SplitKeyMethodTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}