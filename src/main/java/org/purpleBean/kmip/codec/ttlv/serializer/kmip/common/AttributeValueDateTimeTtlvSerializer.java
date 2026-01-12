package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeTtlvSerializer extends AbstractKmipTtlvSerializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeTtlvSerializer() {
        super(AttributeValueDateTime::getValue);
    }
}