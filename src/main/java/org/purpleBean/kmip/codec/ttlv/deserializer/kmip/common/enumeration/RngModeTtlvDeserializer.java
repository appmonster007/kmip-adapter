package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeTtlvDeserializer extends AbstractKmipTtlvDeserializer<RngMode, Integer> {

    public RngModeTtlvDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType, Integer.class, value -> new RngMode(RngMode.fromValue(value)));
    }
}