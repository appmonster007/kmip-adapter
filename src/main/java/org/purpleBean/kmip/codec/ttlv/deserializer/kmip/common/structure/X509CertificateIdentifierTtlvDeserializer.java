package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.CertificateSerialNumber;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class X509CertificateIdentifierTtlvDeserializer extends KmipDataTypeTtlvDeserializer<X509CertificateIdentifier> {
    private final KmipTag kmipTag = X509CertificateIdentifier.kmipTag;
    private final EncodingType encodingType = X509CertificateIdentifier.encodingType;

    @Override
    public X509CertificateIdentifier deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        X509CertificateIdentifier.X509CertificateIdentifierBuilder builder = X509CertificateIdentifier.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        X509CertificateIdentifier x509CertificateIdentifier = builder.build();
        if (!x509CertificateIdentifier.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", x509CertificateIdentifier.getClass().getSimpleName(), spec));
        }
        return x509CertificateIdentifier;
    }

    private void setValue(
            X509CertificateIdentifier.X509CertificateIdentifierBuilder builder,
            KmipTag.Value nodeTag,
            TtlvObject ttlvObject,
            TtlvMapper mapper
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(mapper.readValue(ttlvObject.toByteBuffer(), IssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER ->
                    builder.certificateSerialNumber(mapper.readValue(ttlvObject.toByteBuffer(), CertificateSerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
