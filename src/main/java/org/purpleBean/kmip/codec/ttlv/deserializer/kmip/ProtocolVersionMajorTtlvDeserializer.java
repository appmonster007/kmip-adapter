package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ProtocolVersionMajorTtlvDeserializer implements TtlvDeserializer<ProtocolVersion.ProtocolVersionMajor> {
    EncodingType type = EncodingType.INTEGER;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION_MAJOR);

    @Override
    public ProtocolVersion.ProtocolVersionMajor deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = bb.getInt();

        return ProtocolVersion.ProtocolVersionMajor.of(value);
    }
}
