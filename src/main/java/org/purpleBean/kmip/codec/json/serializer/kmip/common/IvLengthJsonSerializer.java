package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.IvLength;

public class IvLengthJsonSerializer extends AbstractKmipJsonSerializer<IvLength, Integer> {

    public IvLengthJsonSerializer() {
        super(IvLength::getValue);
    }
}