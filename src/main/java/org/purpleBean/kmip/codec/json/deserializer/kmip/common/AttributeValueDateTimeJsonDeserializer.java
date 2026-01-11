package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeJsonDeserializer() {
        super(AttributeValueDateTime.kmipTag, AttributeValueDateTime.encodingType, OffsetDateTime.class, value -> AttributeValueDateTime.builder().value(value).build());
    }
}