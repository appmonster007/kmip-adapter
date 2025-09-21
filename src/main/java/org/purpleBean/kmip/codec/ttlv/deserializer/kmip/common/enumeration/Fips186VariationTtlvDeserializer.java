package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * TTLV deserializer for Fips186Variation.
 */
public class Fips186VariationTtlvDeserializer extends KmipDataTypeTtlvDeserializer<Fips186Variation> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FIPS186_VARIATION);

    @Override
    public Fips186Variation deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for Fips186Variation", encodingType.getTypeValue()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        Fips186Variation fips186variation = new Fips186Variation(Fips186Variation.fromValue(spec, value));

        if (!fips186variation.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return fips186variation;
    }
}
