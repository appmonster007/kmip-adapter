package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class AttributeTtlvDeserializer extends KmipDataTypeTtlvDeserializer<Attribute> {
    private final EncodingType type = EncodingType.STRUCTURE;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE);

    @Override
    public Attribute deserialize(java.nio.ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", type.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();

        TtlvObject attrNameNode = null;
        TtlvObject attrIndexNode = null;
        TtlvObject attrValueNode = null;

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            switch (nodeTag) {
                case KmipTag.Standard.ATTRIBUTE_NAME -> attrNameNode = ttlvObject;
                case KmipTag.Standard.ATTRIBUTE_INDEX -> attrIndexNode = ttlvObject;
                case KmipTag.Standard.ATTRIBUTE_VALUE -> attrValueNode = ttlvObject;
                default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
            }
        }

        if (attrNameNode == null || attrValueNode == null) {
            throw new IllegalArgumentException("Missing required fields for Attribute");
        }


        Attribute.AttributeName attrName = mapper.readValue(attrNameNode.toByteBuffer(), Attribute.AttributeName.class);
        String name = StringUtils.covertTitleToPascalCase(attrName.getName());

        EncodingType encodingType = EncodingType.fromTypeValue(attrValueNode.getType()).get();

        KmipAttribute attrValue;

        if (Attribute.isCustomAttribute(name)) {
            attrValue = buildCustomAttribute(attrValueNode, mapper, name, encodingType);
        } else {
            KmipTag.Value kmipTagValue = KmipTag.fromName(spec, name);
            Class<?> attrClass = KmipAttribute.getClassFromRegistry(spec, kmipTagValue, encodingType);

            attrValueNode.setTag(new KmipTag(kmipTagValue).getTagBytes());
            attrValue = (KmipAttribute) mapper.readValue(attrValueNode.toByteBuffer(), attrClass);
        }

        Attribute attribute;
        if (attrIndexNode == null) {
            attribute = Attribute.of(attrValue);
        } else {
            Attribute.AttributeIndex attrIndex = mapper.readValue(attrIndexNode.toByteBuffer(), Attribute.AttributeIndex.class);
            attribute = Attribute.of(attrValue, attrIndex.getIndex());
        }

        if (!attribute.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", attribute.getClass().getSimpleName(), spec));
        }
        return attribute;
    }

    private Attribute.CustomAttribute buildCustomAttribute(TtlvObject ttlvObject, TtlvMapper mapper, String name, EncodingType encodingType) throws IOException {
        Object obj;
        final byte[] value = ttlvObject.getValue();
        switch (encodingType) {
            case INTEGER, ENUMERATION, INTERVAL -> obj = mapper.readValue(value, Integer.class);
            case BOOLEAN -> obj = mapper.readValue(value, Boolean.class);
            case DATE_TIME -> obj = mapper.readValue(value, OffsetDateTime.class);
            case LONG_INTEGER -> obj = mapper.readValue(value, Long.class);
            case TEXT_STRING -> obj = mapper.readValue(value, String.class);
            case BYTE_STRING -> obj = mapper.readValue(value, ByteBuffer.class);
            case BIG_INTEGER -> obj = mapper.readValue(value, BigInteger.class);
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        }

        return Attribute.CustomAttribute.of(name, encodingType, obj);
    }
}
