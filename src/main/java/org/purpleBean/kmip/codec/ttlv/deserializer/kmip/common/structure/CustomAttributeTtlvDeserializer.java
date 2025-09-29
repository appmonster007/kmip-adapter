package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.structure.CustomAttribute;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomAttributeTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CustomAttribute> {
    private final KmipTag kmipTag = CustomAttribute.kmipTag;
    private final EncodingType encodingType = CustomAttribute.encodingType;

    @Override
    public CustomAttribute deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        CustomAttribute.CustomAttributeBuilder builder = CustomAttribute.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        CustomAttribute attribute = builder.build();

        if (!attribute.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", attribute.getClass().getSimpleName(), spec));
        }
        return attribute;
    }

    private void setValue(CustomAttribute.CustomAttributeBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME ->
                    builder.attributeName(mapper.readValue(ttlvObject.toByteBuffer(), AttributeName.class));
            case KmipTag.Standard.ATTRIBUTE_VALUE ->
                    builder.attributeValue(mapper.readValue(ttlvObject.toByteBuffer(), AttributeValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
