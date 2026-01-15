package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeTtlvSerializer() {
        super(AttributeValueDateTime::getValue);
    }
}