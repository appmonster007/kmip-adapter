package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<WrappingMethod, Integer> {

    public WrappingMethodTtlvDeserializer() {
        super(WrappingMethod.kmipTag, WrappingMethod.encodingType, Integer.class, value -> new WrappingMethod(WrappingMethod.fromValue(value)));
    }
}