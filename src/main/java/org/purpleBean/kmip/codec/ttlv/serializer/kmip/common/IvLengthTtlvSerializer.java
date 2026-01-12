package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthTtlvSerializer extends AbstractKmipTtlvSerializer<IvLength, Integer> {

    public IvLengthTtlvSerializer() {
        super(IvLength::getValue);
    }
}