package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

public class InvocationFieldLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthTtlvSerializer() {
        super(InvocationFieldLength::getValue);
    }
}