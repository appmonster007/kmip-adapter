package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class AttributeValueByteStringTtlvDeserializer extends KmipDataTypeTtlvDeserializer<AttributeValue.ByteString> {
    private final KmipTag kmipTag = AttributeValue.ByteString.kmipTag;
    private final EncodingType encodingType = AttributeValue.ByteString.encodingType;

    @Override
    public AttributeValue.ByteString deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        AttributeValue.ByteString attributeValueByteString = AttributeValue.ByteString.of(bb);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueByteString.isSupported()) {
            throw new NoSuchElementException(String.format("AttributeValue.ByteString not supported for spec %s", spec));
        }
        return attributeValueByteString;
    }
}
