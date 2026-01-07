package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.CertificateIssuer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CertificateIssuerTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CertificateIssuer> {
    private final KmipTag kmipTag = CertificateIssuer.kmipTag;
    private final EncodingType encodingType = CertificateIssuer.encodingType;

    @Override
    public CertificateIssuer deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        CertificateIssuer.CertificateIssuerBuilder builder = CertificateIssuer.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        CertificateIssuer certificateissuer = builder.build();
        if (!certificateissuer.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", certificateissuer.getClass().getSimpleName(), spec));
        }
        return certificateissuer;
    }

    private void setValue(
            CertificateIssuer.CertificateIssuerBuilder builder,
            KmipTag.Value nodeTag,
            TtlvObject ttlvObject,
            TtlvMapper mapper
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_ISSUER_DISTINGUISHED_NAME ->
                    builder.certificateIssuerDistinguishedName(mapper.readValue(ttlvObject.toByteBuffer(), CertificateIssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_ISSUER_ALTERNATIVE_NAME ->
                    builder.certificateIssuerAlternativeName(mapper.readValue(ttlvObject.toByteBuffer(), CertificateIssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}