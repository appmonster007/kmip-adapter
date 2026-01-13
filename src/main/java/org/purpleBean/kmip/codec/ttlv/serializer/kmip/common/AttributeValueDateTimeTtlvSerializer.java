package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueDateTime, OffsetDateTime> {

    public AttributeValueDateTimeTtlvSerializer() {
        super(AttributeValueDateTime::getValue);
    }
}