package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueEnumerationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueEnumeration, AttributeValueEnumeration.AttributeValueEnumerationBuilder> {

    public AttributeValueEnumerationTtlvDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType);
    }

    @Override
    protected AttributeValueEnumeration.AttributeValueEnumerationBuilder createBuilder() {
        return AttributeValueEnumeration.builder();
    }

    @Override
    protected void setValue(AttributeValueEnumeration.AttributeValueEnumerationBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        int value = mapper.readValue(byteBuffer, Integer.class);
        String attributeName = (String) mapper.getAttribute("attributeName");
        if (attributeName == null) {
            throw new IllegalArgumentException("Attribute name not found in mapper context for AttributeValueEnumeration deserialization.");
        }
        KmipTag.Value enumType = KmipTag.fromName(StringUtils.covertTitleToPascalCase(attributeName));
        KmipEnumeration.Value<?> enumValue = KmipEnumeration.getFromValue(enumType).apply(value);
        builder.value(enumValue);
    }

    @Override
    protected AttributeValueEnumeration build(AttributeValueEnumeration.AttributeValueEnumerationBuilder builder) {
        return builder.build();
    }
}
