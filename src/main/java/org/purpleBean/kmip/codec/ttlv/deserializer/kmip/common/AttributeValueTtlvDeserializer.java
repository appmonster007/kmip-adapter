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
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
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

        ByteBuffer bb = ByteBuffer.wrap(ttlvObject.getValue()).order(TtlvConstants.BYTE_ORDER);

        Object obj;
        switch (encodingType) {
            case INTEGER, ENUMERATION, INTERVAL -> obj = mapper.readValue(bb, Integer.class);
            case BOOLEAN -> obj = mapper.readValue(bb, Boolean.class);
            case DATE_TIME -> obj = mapper.readValue(bb, OffsetDateTime.class);
            case LONG_INTEGER -> obj = mapper.readValue(bb, Long.class);
            case TEXT_STRING -> obj = mapper.readValue(bb, String.class);
            case BYTE_STRING -> obj = mapper.readValue(bb, ByteBuffer.class);
            case BIG_INTEGER -> obj = mapper.readValue(bb, BigInteger.class);
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        }
        AttributeValue attributeValue = AttributeValue.builder().encodingType(encodingType).value(obj).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValue.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", attributeValue.getClass().getSimpleName(), spec));
        }
        return attributeValue;
    }
}
