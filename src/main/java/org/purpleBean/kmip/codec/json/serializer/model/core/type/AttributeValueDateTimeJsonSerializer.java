package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeJsonSerializer() {
        super(AttributeValueDateTime::getValue);
    }
}