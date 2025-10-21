package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.CryptographicUsageMask;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CryptographicUsageMaskTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CryptographicUsageMask> {
    private final KmipTag kmipTag = CryptographicUsageMask.kmipTag;
    private final EncodingType encodingType = CryptographicUsageMask.encodingType;

    @Override
    public CryptographicUsageMask deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);

        Integer value = mapper.readValue(bb, Integer.class);
        CryptographicUsageMask cryptographicUsageMask = CryptographicUsageMask.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!cryptographicUsageMask.isSupported()) {
            throw new NoSuchElementException(String.format("CryptographicUsageMask not supported for spec %s", spec));
        }
        return cryptographicUsageMask;
    }
}
