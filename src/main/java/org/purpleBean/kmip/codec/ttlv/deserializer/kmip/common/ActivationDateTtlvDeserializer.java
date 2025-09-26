package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ActivationDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ActivationDateTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ActivationDate> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ACTIVATION_DATE);
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public ActivationDate deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", encodingType.getTypeValue(), kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        OffsetDateTime dt = mapper.readValue(bb, OffsetDateTime.class);

        KmipSpec spec = KmipContext.getSpec();
        ActivationDate attribute = ActivationDate.builder().dateTime(dt).build();

        if (!attribute.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return attribute;
    }
}
