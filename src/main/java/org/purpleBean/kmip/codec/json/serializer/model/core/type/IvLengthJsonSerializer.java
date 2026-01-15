package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.IvLength;

public class IvLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IvLength, Integer> {

    public IvLengthJsonSerializer() {
        super(IvLength::getValue);
    }
}