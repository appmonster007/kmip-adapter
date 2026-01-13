package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeXmlDeserializer() {
        super(AttributeValueDateTime.kmipTag, AttributeValueDateTime.encodingType, OffsetDateTime.class, value -> AttributeValueDateTime.builder().value(value).build());
    }
}