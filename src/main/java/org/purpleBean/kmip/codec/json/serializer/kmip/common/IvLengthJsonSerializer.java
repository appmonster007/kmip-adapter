package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IvLength, Integer> {

    public IvLengthJsonSerializer() {
        super(IvLength::getValue);
    }
}