package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Offset;

public class OffsetJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Offset, Integer> {

    public OffsetJsonSerializer() {
        super(Offset::getValue);
    }
}