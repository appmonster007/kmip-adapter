package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UnwrapMode, Integer> {

    public UnwrapModeTtlvDeserializer() {
        super(UnwrapMode.kmipTag, UnwrapMode.encodingType, Integer.class, value -> new UnwrapMode(UnwrapMode.fromValue(value)));
    }
}