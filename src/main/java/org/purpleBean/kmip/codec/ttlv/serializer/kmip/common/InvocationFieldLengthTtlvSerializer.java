package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthTtlvSerializer extends AbstractKmipTtlvSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthTtlvSerializer() {
        super(InvocationFieldLength::getValue);
    }
}