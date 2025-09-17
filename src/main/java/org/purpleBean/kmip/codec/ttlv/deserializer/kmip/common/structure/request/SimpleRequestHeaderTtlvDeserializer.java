package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleRequestHeaderTtlvDeserializer extends KmipDataTypeTtlvDeserializer<SimpleRequestHeader> {
    EncodingType type = EncodingType.STRUCTURE;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.REQUEST_HEADER);

    @Override
    public SimpleRequestHeader deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestHeader.SimpleRequestHeaderBuilder builder = SimpleRequestHeader.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        SimpleRequestHeader simpleRequestHeader = builder.build();

        if (!simpleRequestHeader.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return simpleRequestHeader;
    }

    private void setValue(SimpleRequestHeader.SimpleRequestHeaderBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION ->
                    builder.protocolVersion(mapper.readValue(ttlvObject.toByteBuffer(), ProtocolVersion.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
