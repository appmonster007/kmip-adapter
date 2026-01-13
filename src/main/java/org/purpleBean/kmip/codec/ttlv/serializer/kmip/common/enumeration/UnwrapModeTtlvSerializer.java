package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UnwrapMode, Integer> {

    public UnwrapModeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}