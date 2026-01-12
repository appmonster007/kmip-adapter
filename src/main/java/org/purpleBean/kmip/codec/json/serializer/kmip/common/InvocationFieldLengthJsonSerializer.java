package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthJsonSerializer extends AbstractKmipJsonSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthJsonSerializer() {
        super(InvocationFieldLength::getValue);
    }
}