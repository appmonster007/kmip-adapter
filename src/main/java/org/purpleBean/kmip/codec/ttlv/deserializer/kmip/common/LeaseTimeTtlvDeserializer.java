package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.LeaseTime;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class LeaseTimeTtlvDeserializer extends KmipDataTypeTtlvDeserializer<LeaseTime> {
    private final KmipTag kmipTag = LeaseTime.kmipTag;
    private final EncodingType encodingType = LeaseTime.encodingType;

    @Override
    public LeaseTime deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        var value = mapper.readValue(bb, Integer.class);
        LeaseTime leaseTime = LeaseTime.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!leaseTime.isSupported()) {
            throw new NoSuchElementException(String.format("LeaseTime not supported for spec %s", spec));
        }
        return leaseTime;
    }
}
