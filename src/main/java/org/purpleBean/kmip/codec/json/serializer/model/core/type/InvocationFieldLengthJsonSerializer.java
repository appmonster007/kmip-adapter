package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

public class InvocationFieldLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthJsonSerializer() {
        super(InvocationFieldLength::getValue);
    }
}