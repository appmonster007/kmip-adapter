package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeJsonSerializer extends AbstractKmipJsonSerializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeJsonSerializer() {
        super(AttributeValueDateTime::getValue);
    }
}