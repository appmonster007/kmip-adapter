package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeXmlSerializer() {
        super(AttributeValueDateTime::getValue);
    }
}