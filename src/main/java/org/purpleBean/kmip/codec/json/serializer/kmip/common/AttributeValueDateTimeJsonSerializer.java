package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeJsonSerializer() {
        super(AttributeValueDateTime::getValue);
    }
}