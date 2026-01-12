package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeTtlvSerializer extends AbstractKmipTtlvSerializer<RngMode, Integer> {

    public RngModeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}