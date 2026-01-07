package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.X509CertificateIssuer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class X509CertificateIssuerTtlvDeserializer extends KmipDataTypeTtlvDeserializer<X509CertificateIssuer> {
    private final KmipTag kmipTag = X509CertificateIssuer.kmipTag;
    private final EncodingType encodingType = X509CertificateIssuer.encodingType;

    @Override
    public X509CertificateIssuer deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        X509CertificateIssuer.X509CertificateIssuerBuilder builder = X509CertificateIssuer.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        X509CertificateIssuer x509certificateissuer = builder.build();
        if (!x509certificateissuer.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", x509certificateissuer.getClass().getSimpleName(), spec));
        }
        return x509certificateissuer;
    }

    private void setValue(
            X509CertificateIssuer.X509CertificateIssuerBuilder builder,
            KmipTag.Value nodeTag,
            TtlvObject ttlvObject,
            TtlvMapper mapper
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(mapper.readValue(ttlvObject.toByteBuffer(), IssuerDistinguishedName.class));
            case KmipTag.Standard.ISSUER_ALTERNATIVE_NAME ->
                    builder.issuerAlternativeName(mapper.readValue(ttlvObject.toByteBuffer(), IssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}