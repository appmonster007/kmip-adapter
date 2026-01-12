package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Offset;

public class OffsetJsonSerializer extends AbstractKmipJsonSerializer<Offset, Integer> {

    public OffsetJsonSerializer() {
        super(Offset::getValue);
    }
}