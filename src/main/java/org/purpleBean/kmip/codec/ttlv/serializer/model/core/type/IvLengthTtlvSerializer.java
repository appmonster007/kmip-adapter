package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.IvLength;

public class IvLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IvLength, Integer> {

    public IvLengthTtlvSerializer() {
        super(IvLength::getValue);
    }
}