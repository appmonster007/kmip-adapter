package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.common.structure.TransparentDsaPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class TransparentDsaPublicKeyTtlvDeserializer extends KmipDataTypeTtlvDeserializer<TransparentDsaPublicKey> {
    private final KmipTag kmipTag = TransparentDsaPublicKey.kmipTag;
    private final EncodingType encodingType = TransparentDsaPublicKey.encodingType;

    @Override
    public TransparentDsaPublicKey deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder = TransparentDsaPublicKey.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        TransparentDsaPublicKey transparentDsaPublicKey = builder.build();

        if (!transparentDsaPublicKey.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", transparentDsaPublicKey.getClass().getSimpleName(), spec));
        }
        return transparentDsaPublicKey;
    }

    private void setValue(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(mapper.readValue(ttlvObject.toByteBuffer(), P.class));
            case KmipTag.Standard.Q -> builder.q(mapper.readValue(ttlvObject.toByteBuffer(), Q.class));
            case KmipTag.Standard.G -> builder.g(mapper.readValue(ttlvObject.toByteBuffer(), G.class));
            case KmipTag.Standard.Y -> builder.y(mapper.readValue(ttlvObject.toByteBuffer(), Y.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}