package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Offset;

public class OffsetJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Offset, Integer> {

    public OffsetJsonSerializer() {
        super(Offset::getValue);
    }
}