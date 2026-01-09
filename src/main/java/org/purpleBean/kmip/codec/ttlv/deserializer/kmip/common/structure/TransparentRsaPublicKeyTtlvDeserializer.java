package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.common.structure.TransparentRsaPublicKey;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class TransparentRsaPublicKeyTtlvDeserializer extends KmipDataTypeTtlvDeserializer<TransparentRsaPublicKey> {
    private final KmipTag kmipTag = TransparentRsaPublicKey.kmipTag;
    private final EncodingType encodingType = TransparentRsaPublicKey.encodingType;

    @Override
    public TransparentRsaPublicKey deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder = TransparentRsaPublicKey.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        TransparentRsaPublicKey transparentRsaPublicKey = builder.build();

        if (!transparentRsaPublicKey.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", transparentRsaPublicKey.getClass().getSimpleName(), spec));
        }
        return transparentRsaPublicKey;
    }

    private void setValue(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS ->
                    builder.modulus(mapper.readValue(ttlvObject.toByteBuffer(), Modulus.class));
            case KmipTag.Standard.PUBLIC_EXPONENT ->
                    builder.publicExponent(mapper.readValue(ttlvObject.toByteBuffer(), PublicExponent.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}