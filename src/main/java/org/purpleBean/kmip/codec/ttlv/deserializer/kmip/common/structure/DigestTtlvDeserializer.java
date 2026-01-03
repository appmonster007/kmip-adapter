package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.structure.Digest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class DigestTtlvDeserializer extends KmipDataTypeTtlvDeserializer<Digest> {
    private final KmipTag kmipTag = Digest.kmipTag;
    private final EncodingType encodingType = Digest.encodingType;

    @Override
    public Digest deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        Digest.DigestBuilder builder = Digest.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        Digest digest = builder.build();
        if (!digest.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", digest.getClass().getSimpleName(), spec));
        }
        return digest;
    }

    private void setValue(Digest.DigestBuilder builder,
                          KmipTag.Value nodeTag,
                          TtlvObject ttlvObject,
                          TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.HASHING_ALGORITHM ->
                    builder.hashingAlgorithm(mapper.readValue(ttlvObject.toByteBuffer(), HashingAlgorithm.class));
            case KmipTag.Standard.DIGEST_VALUE ->
                    builder.digestValue(mapper.readValue(ttlvObject.toByteBuffer(), DigestValue.class));
            case KmipTag.Standard.KEY_FORMAT_TYPE ->
                    builder.keyFormatType(mapper.readValue(ttlvObject.toByteBuffer(), KeyFormatType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
