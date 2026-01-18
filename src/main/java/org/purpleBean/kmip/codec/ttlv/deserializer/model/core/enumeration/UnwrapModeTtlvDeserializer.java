package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;

public class UnwrapModeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UnwrapMode, Integer> {

    public UnwrapModeTtlvDeserializer() {
        super(UnwrapMode.kmipTag, UnwrapMode.encodingType, Integer.class, value -> new UnwrapMode(UnwrapMode.fromValue(value)));
    }
}