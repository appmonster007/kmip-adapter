package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RngMode, Integer> {

    public RngModeTtlvSerializer() {
        super(RngMode::getValue);
    }
}