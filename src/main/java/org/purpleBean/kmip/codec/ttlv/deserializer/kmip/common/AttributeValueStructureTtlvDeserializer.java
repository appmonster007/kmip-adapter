package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class AttributeValueStructureTtlvDeserializer extends KmipDataTypeTtlvDeserializer<AttributeValue.Structure> {
    private final KmipTag kmipTag = AttributeValue.Structure.kmipTag;
    private final EncodingType encodingType = AttributeValue.Structure.encodingType;

    @Override
    public AttributeValue.Structure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        List<KmipDataType> values = new ArrayList<>();
        for (TtlvObject ttlvObject : nestedObjects) {
            values.add(deserializeObjects(mapper, ttlvObject));
        }
        AttributeValue.Structure attributeValueStructure = AttributeValue.Structure.of(values);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueStructure.isSupported()) {
            throw new NoSuchElementException(String.format("AttributeValue.Structure not supported for spec %s", spec));
        }
        return attributeValueStructure;
    }

    private KmipDataType deserializeObjects(TtlvMapper mapper, TtlvObject node) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        KmipTag.Value nodeTag = KmipTag.fromBytes(spec, node.getTag());
        EncodingType encodingType = EncodingType.fromTypeValue(node.getType()).get();
        Class<? extends KmipDataType> dataType = KmipDataType.getClassFromRegistry(nodeTag, encodingType);
        return mapper.readValue(node.toByteBuffer(), dataType);
    }
}
