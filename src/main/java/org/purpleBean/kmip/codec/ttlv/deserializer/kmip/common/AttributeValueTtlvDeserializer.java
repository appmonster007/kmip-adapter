package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class AttributeValueTtlvDeserializer extends KmipDataTypeTtlvDeserializer<AttributeValue> {

    private final KmipTag kmipTag = AttributeValue.kmipTag;

    @Override
    public AttributeValue deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject ttlvObject = TtlvObject.fromBuffer(ttlvBuffer);
        if (!Arrays.equals(ttlvObject.getTag(), kmipTag.getTagBytes())) {
            throw new IllegalArgumentException(String.format("Expected tag %s for %s", kmipTag.getTagHexString(), kmipTag.getDescription()));
        }

        EncodingType encodingType = EncodingType.fromTypeValue(ttlvObject.getType()).get();

        KmipSpec spec = KmipContext.getSpec();
        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(kmipTag.getValue(), encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTag.getValue(), encodingType));
        }

        ttlvBuffer.rewind();
        AttributeValue attributeValue = (AttributeValue) mapper.readValue(ttlvBuffer, clazz);

        if (!attributeValue.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", attributeValue.getClass().getSimpleName(), spec));
        }
        return attributeValue;
    }
}
