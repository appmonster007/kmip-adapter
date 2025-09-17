package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class KeyCompressionTypeTtlvDeserializer extends KmipDataTypeTtlvDeserializer<KeyCompressionType> {
    private final EncodingType type = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_COMPRESSION_TYPE);

    @Override
    public KeyCompressionType deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(
                    String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int raw = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        KeyCompressionType.Value enumValue = KeyCompressionType.fromValue(spec, raw);
        KeyCompressionType result = new KeyCompressionType(enumValue);

        if (!result.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("Value '%d' is not supported for KMIP spec %s", raw, spec));
        }
        return result;
    }
}
