package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;

public class FixedFieldLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<FixedFieldLength, Integer> {

    public FixedFieldLengthTtlvSerializer() {
        super(FixedFieldLength::getValue);
    }
}