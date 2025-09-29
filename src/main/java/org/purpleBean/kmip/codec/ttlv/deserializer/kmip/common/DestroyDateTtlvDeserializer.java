package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.DestroyDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class DestroyDateTtlvDeserializer extends KmipDataTypeTtlvDeserializer<DestroyDate> {
    private final KmipTag kmipTag = DestroyDate.kmipTag;
    private final EncodingType encodingType = DestroyDate.encodingType;

    @Override
    public DestroyDate deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        // TODO : update with required java type
        OffsetDateTime dt = mapper.readValue(bb, OffsetDateTime.class);
        DestroyDate destroyDate = DestroyDate.builder().value(dt).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!destroyDate.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", destroyDate.getClass().getSimpleName(), spec));
        }
        return destroyDate;
    }
}
