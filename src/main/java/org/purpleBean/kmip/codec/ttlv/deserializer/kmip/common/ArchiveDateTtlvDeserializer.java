package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ArchiveDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArchiveDateTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ArchiveDate> {
    private final KmipTag kmipTag = ArchiveDate.kmipTag;
    private final EncodingType encodingType = ArchiveDate.encodingType;

    @Override
    public ArchiveDate deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        // TODO : update with required java type
        OffsetDateTime dt = mapper.readValue(bb, OffsetDateTime.class);
        ArchiveDate archiveDate = ArchiveDate.builder().value(dt).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!archiveDate.isSupported()) {
            throw new NoSuchElementException(String.format("ArchiveDate not supported for spec %s", spec));
        }
        return archiveDate;
    }
}
