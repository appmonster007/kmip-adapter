package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;

public class UnwrapModeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UnwrapMode, Integer> {

    public UnwrapModeTtlvSerializer() {
        super(UnwrapMode::getIntValue);
    }
}