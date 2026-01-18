package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;

public class SplitKeyMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SplitKeyMethod, Integer> {

    public SplitKeyMethodTtlvDeserializer() {
        super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType, Integer.class, value -> new SplitKeyMethod(SplitKeyMethod.fromValue(value)));
    }
}