package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeXmlDeserializer() {
        super(AttributeValueDateTime.kmipTag, AttributeValueDateTime.encodingType, OffsetDateTime.class, value -> AttributeValueDateTime.builder().value(value).build());
    }
}