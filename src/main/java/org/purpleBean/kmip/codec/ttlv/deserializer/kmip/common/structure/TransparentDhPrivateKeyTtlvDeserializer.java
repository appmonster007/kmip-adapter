package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentDhPrivateKey;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class TransparentDhPrivateKeyTtlvDeserializer extends KmipDataTypeTtlvDeserializer<TransparentDhPrivateKey> {
    private final KmipTag kmipTag = TransparentDhPrivateKey.kmipTag;
    private final EncodingType encodingType = TransparentDhPrivateKey.encodingType;

    @Override
    public TransparentDhPrivateKey deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder = TransparentDhPrivateKey.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        TransparentDhPrivateKey transparentDhPrivateKey = builder.build();

        if (!transparentDhPrivateKey.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", transparentDhPrivateKey.getClass().getSimpleName(), spec));
        }
        return transparentDhPrivateKey;
    }

    private void setValue(TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(mapper.readValue(ttlvObject.toByteBuffer(), P.class));
            case KmipTag.Standard.Q -> builder.q(mapper.readValue(ttlvObject.toByteBuffer(), Q.class));
            case KmipTag.Standard.G -> builder.g(mapper.readValue(ttlvObject.toByteBuffer(), G.class));
            case KmipTag.Standard.J -> builder.j(mapper.readValue(ttlvObject.toByteBuffer(), J.class));
            case KmipTag.Standard.X -> builder.x(mapper.readValue(ttlvObject.toByteBuffer(), X.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}