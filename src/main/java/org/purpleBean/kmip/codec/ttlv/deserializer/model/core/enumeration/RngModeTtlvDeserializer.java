package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngMode;

public class RngModeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RngMode, Integer> {

    public RngModeTtlvDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType, Integer.class, value -> RngMode.fromValue(value).inst());
    }
}