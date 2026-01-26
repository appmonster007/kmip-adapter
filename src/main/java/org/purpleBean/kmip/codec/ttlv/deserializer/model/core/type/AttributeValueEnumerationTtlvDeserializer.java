package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class AttributeValueEnumerationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationTtlvDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType, Integer.class, null);
    }

    @Override
    public AttributeValueEnumeration deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), AttributeValueEnumeration.kmipTag.getTagBytes())
                && obj.getType() != AttributeValueEnumeration.encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", AttributeValueEnumeration.encodingType.getTypeValue(), AttributeValueEnumeration.kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = mapper.readValue(bb, Integer.class);
        String attributeName = (String) mapper.getAttribute("attributeName");
        KmipTag.Value enumType = KmipTag.fromName(StringUtils.covertTitleToPascalCase(attributeName));
        if (enumType == null) {
            throw new IllegalArgumentException("Invalid attribute name for " + handledType().getSimpleName());
        }
        KmipEnumeration.Value<?> enumValue = KmipEnumeration.getFromValue(enumType).apply(value);
        AttributeValueEnumeration result = AttributeValueEnumeration.builder().value(enumValue).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupported()) {
            throw new NoSuchElementException(String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
        }
        return result;
    }
}