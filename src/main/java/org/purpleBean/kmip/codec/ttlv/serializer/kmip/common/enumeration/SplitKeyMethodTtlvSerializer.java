package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SplitKeyMethod, Integer> {

    public SplitKeyMethodTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}