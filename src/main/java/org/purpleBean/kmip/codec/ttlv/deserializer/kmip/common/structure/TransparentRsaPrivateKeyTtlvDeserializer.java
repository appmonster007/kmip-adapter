package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentRsaPrivateKey;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class TransparentRsaPrivateKeyTtlvDeserializer extends KmipDataTypeTtlvDeserializer<TransparentRsaPrivateKey> {
    private final KmipTag kmipTag = TransparentRsaPrivateKey.kmipTag;
    private final EncodingType encodingType = TransparentRsaPrivateKey.encodingType;

    @Override
    public TransparentRsaPrivateKey deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder = TransparentRsaPrivateKey.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        TransparentRsaPrivateKey transparentRsaPrivateKey = builder.build();

        if (!transparentRsaPrivateKey.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", transparentRsaPrivateKey.getClass().getSimpleName(), spec));
        }
        return transparentRsaPrivateKey;
    }

    private void setValue(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS ->
                    builder.modulus(mapper.readValue(ttlvObject.toByteBuffer(), Modulus.class));
            case KmipTag.Standard.PRIVATE_EXPONENT ->
                    builder.privateExponent(mapper.readValue(ttlvObject.toByteBuffer(), PrivateExponent.class));
            case KmipTag.Standard.PUBLIC_EXPONENT ->
                    builder.publicExponent(mapper.readValue(ttlvObject.toByteBuffer(), PublicExponent.class));
            case KmipTag.Standard.P -> builder.p(mapper.readValue(ttlvObject.toByteBuffer(), P.class));
            case KmipTag.Standard.Q -> builder.q(mapper.readValue(ttlvObject.toByteBuffer(), Q.class));
            case KmipTag.Standard.PRIME_EXPONENT_P ->
                    builder.primeExponentP(mapper.readValue(ttlvObject.toByteBuffer(), PrimeExponentP.class));
            case KmipTag.Standard.PRIME_EXPONENT_Q ->
                    builder.primeExponentQ(mapper.readValue(ttlvObject.toByteBuffer(), PrimeExponentQ.class));
            case KmipTag.Standard.CRT_COEFFICIENT ->
                    builder.crtCoefficient(mapper.readValue(ttlvObject.toByteBuffer(), CRTCoefficient.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}