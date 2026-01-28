package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.RngMode;

public class RngModeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RngMode, Integer> {

    public RngModeTtlvSerializer() {
        super(RngMode::getIntValue);
    }
}