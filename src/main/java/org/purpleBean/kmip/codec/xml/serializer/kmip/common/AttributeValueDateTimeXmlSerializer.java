package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeXmlSerializer() {
        super(AttributeValueDateTime::getValue);
    }
}