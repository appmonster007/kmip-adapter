package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeTtlvDeserializer extends AbstractKmipTtlvDeserializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeTtlvDeserializer() {
        super(AttributeValueDateTime.kmipTag, AttributeValueDateTime.encodingType, OffsetDateTime.class, value -> AttributeValueDateTime.builder().value(value).build());
    }
}