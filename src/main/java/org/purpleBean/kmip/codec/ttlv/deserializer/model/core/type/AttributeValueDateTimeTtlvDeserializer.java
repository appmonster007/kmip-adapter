package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeTtlvDeserializer() {
        super(AttributeValueDateTime.kmipTag, AttributeValueDateTime.encodingType, OffsetDateTime.class, value -> AttributeValueDateTime.builder().value(value).build());
    }
}