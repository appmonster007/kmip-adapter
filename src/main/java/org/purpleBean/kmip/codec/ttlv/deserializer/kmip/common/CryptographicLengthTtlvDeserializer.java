package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.CryptographicLength;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class CryptographicLengthTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CryptographicLength> {
    private final KmipTag kmipTag = CryptographicLength.kmipTag;
    private final EncodingType encodingType = CryptographicLength.encodingType;

    @Override
    public CryptographicLength deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        // Read the integer value for cryptographic length (in bits)
        if (obj.getValue().length != 4) {
            throw new IllegalArgumentException("Invalid length for CryptographicLength value, expected 4 bytes");
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int length = bb.getInt();
        if (length < 0) {
            throw new IllegalArgumentException("CryptographicLength value must be a non-negative integer");
        }
        CryptographicLength cryptographicLength = CryptographicLength.of(length);

        KmipSpec spec = KmipContext.getSpec();

        if (!cryptographicLength.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", cryptographicLength.getClass().getSimpleName(), spec));
        }
        return cryptographicLength;
    }
}
