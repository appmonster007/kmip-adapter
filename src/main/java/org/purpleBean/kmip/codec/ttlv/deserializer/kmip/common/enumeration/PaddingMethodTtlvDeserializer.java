package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PaddingMethod, Integer> {

    public PaddingMethodTtlvDeserializer() {
        super(PaddingMethod.kmipTag, PaddingMethod.encodingType, Integer.class, value -> new PaddingMethod(PaddingMethod.fromValue(value)));
    }
}