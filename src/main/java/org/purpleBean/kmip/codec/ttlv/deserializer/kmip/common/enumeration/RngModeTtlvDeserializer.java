package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RngMode, Integer> {

    public RngModeTtlvDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType, Integer.class, value -> new RngMode(RngMode.fromValue(value)));
    }
}