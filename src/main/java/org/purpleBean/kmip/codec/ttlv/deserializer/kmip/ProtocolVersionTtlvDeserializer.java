package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ProtocolVersionTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ProtocolVersion> {
    EncodingType type = EncodingType.STRUCTURE;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION);

    @Override
    public ProtocolVersion deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());

        KmipSpec spec = KmipContext.getSpec();
        ProtocolVersion.ProtocolVersionBuilder builder = ProtocolVersion.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        ProtocolVersion protocolVersion = builder.build();

        if (!protocolVersion.isSupported()) {
            throw new NoSuchElementException();
        }

        return protocolVersion;
    }

    private void setValue(ProtocolVersion.ProtocolVersionBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION_MAJOR ->
                    builder.protocolVersionMajor(mapper.readValue(ttlvObject.toByteBuffer(), ProtocolVersion.ProtocolVersionMajor.class));
            case KmipTag.Standard.PROTOCOL_VERSION_MINOR ->
                    builder.protocolVersionMinor(mapper.readValue(ttlvObject.toByteBuffer(), ProtocolVersion.ProtocolVersionMinor.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
