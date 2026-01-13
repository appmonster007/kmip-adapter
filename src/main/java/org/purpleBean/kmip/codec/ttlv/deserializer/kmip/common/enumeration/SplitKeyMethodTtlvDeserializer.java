package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SplitKeyMethod, Integer> {

    public SplitKeyMethodTtlvDeserializer() {
        super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType, Integer.class, value -> new SplitKeyMethod(SplitKeyMethod.fromValue(value)));
    }
}