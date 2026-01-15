package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;

public class PaddingMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PaddingMethod, Integer> {

    public PaddingMethodTtlvDeserializer() {
        super(PaddingMethod.kmipTag, PaddingMethod.encodingType, Integer.class, value -> new PaddingMethod(PaddingMethod.fromValue(value)));
    }
}