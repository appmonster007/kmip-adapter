package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IvLength, Integer> {

    public IvLengthTtlvSerializer() {
        super(IvLength::getValue);
    }
}