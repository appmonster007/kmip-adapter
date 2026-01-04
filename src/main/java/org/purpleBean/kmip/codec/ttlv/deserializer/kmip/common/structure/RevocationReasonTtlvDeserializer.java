package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.common.structure.RevocationReason;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class RevocationReasonTtlvDeserializer extends KmipDataTypeTtlvDeserializer<RevocationReason> {
    private final KmipTag kmipTag = RevocationReason.kmipTag;
    private final EncodingType encodingType = RevocationReason.encodingType;

    @Override
    public RevocationReason deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        RevocationReason.RevocationReasonBuilder builder = RevocationReason.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        RevocationReason revocationReason = builder.build();
        if (!revocationReason.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", revocationReason.getClass().getSimpleName(), spec));
        }
        return revocationReason;
    }

    private void setValue(RevocationReason.RevocationReasonBuilder builder,
                          KmipTag.Value nodeTag,
                          TtlvObject ttlvObject,
                          TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.REVOCATION_REASON_CODE ->
                    builder.revocationReasonCode(mapper.readValue(ttlvObject.toByteBuffer(), RevocationReasonCode.class));
            case KmipTag.Standard.REVOCATION_MESSAGE ->
                    builder.revocationMessage(mapper.readValue(ttlvObject.toByteBuffer(), RevocationMessage.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
