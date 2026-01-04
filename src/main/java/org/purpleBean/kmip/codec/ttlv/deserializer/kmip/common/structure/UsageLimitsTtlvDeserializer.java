package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.common.structure.UsageLimits;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class UsageLimitsTtlvDeserializer extends KmipDataTypeTtlvDeserializer<UsageLimits> {
    private final KmipTag kmipTag = UsageLimits.kmipTag;
    private final EncodingType encodingType = UsageLimits.encodingType;

    @Override
    public UsageLimits deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        UsageLimits.UsageLimitsBuilder builder = UsageLimits.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        UsageLimits usageLimits = builder.build();
        if (!usageLimits.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", usageLimits.getClass().getSimpleName(), spec));
        }
        return usageLimits;
    }

    private void setValue(UsageLimits.UsageLimitsBuilder builder,
                          KmipTag.Value nodeTag,
                          TtlvObject ttlvObject,
                          TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.USAGE_LIMITS_TOTAL ->
                    builder.usageLimitsTotal(mapper.readValue(ttlvObject.toByteBuffer(), UsageLimitsTotal.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT ->
                    builder.usageLimitsCount(mapper.readValue(ttlvObject.toByteBuffer(), UsageLimitsCount.class));
            case KmipTag.Standard.USAGE_LIMITS_UNIT ->
                    builder.usageLimitsUnit(mapper.readValue(ttlvObject.toByteBuffer(), UsageLimitsUnit.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
