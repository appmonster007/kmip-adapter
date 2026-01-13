package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthTtlvSerializer() {
        super(InvocationFieldLength::getValue);
    }
}