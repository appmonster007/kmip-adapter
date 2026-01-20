package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

public class WrappingMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<WrappingMethod, Integer> {

    public WrappingMethodTtlvDeserializer() {
        super(WrappingMethod.kmipTag, WrappingMethod.encodingType, Integer.class, value -> WrappingMethod.fromValue(value).inst());
    }
}