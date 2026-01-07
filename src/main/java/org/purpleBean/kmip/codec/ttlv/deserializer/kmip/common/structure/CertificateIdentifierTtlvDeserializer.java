package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.common.structure.CertificateIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CertificateIdentifierTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CertificateIdentifier> {
    private final KmipTag kmipTag = CertificateIdentifier.kmipTag;
    private final EncodingType encodingType = CertificateIdentifier.encodingType;

    @Override
    public CertificateIdentifier deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        CertificateIdentifier.CertificateIdentifierBuilder builder = CertificateIdentifier.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        CertificateIdentifier certificateidentifier = builder.build();
        if (!certificateidentifier.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", certificateidentifier.getClass().getSimpleName(), spec));
        }
        return certificateidentifier;
    }

    private void setValue(
            CertificateIdentifier.CertificateIdentifierBuilder builder,
            KmipTag.Value nodeTag,
            TtlvObject ttlvObject,
            TtlvMapper mapper
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER -> builder.issuer(mapper.readValue(ttlvObject.toByteBuffer(), Issuer.class));
            case KmipTag.Standard.SERIAL_NUMBER ->
                    builder.serialNumber(mapper.readValue(ttlvObject.toByteBuffer(), SerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}