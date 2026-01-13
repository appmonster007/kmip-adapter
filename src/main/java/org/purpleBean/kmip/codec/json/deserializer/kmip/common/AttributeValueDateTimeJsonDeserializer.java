package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeJsonDeserializer() {
        super(AttributeValueDateTime.kmipTag, AttributeValueDateTime.encodingType, OffsetDateTime.class, value -> AttributeValueDateTime.builder().value(value).build());
    }
}