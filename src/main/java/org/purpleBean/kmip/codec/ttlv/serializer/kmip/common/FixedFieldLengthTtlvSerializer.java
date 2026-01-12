package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthTtlvSerializer extends AbstractKmipTtlvSerializer<FixedFieldLength, Integer> {

    public FixedFieldLengthTtlvSerializer() {
        super(FixedFieldLength::getValue);
    }
}