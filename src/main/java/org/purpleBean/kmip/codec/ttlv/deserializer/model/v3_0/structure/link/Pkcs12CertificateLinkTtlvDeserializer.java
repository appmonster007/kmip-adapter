package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.link;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.structure.link.Pkcs12CertificateLink;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Pkcs12CertificateLinkTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Pkcs12CertificateLink, Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder> {

    public Pkcs12CertificateLinkTtlvDeserializer() {
        super(Pkcs12CertificateLink.kmipTag, Pkcs12CertificateLink.encodingType);
    }

    @Override
    protected Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder createBuilder() {
        return Pkcs12CertificateLink.builder();
    }

    @Override
    protected void setValue(Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // TODO: Implement setting values on the builder based on the tag
        // KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        // switch (nodeTag) {
        //     case KmipTag.Standard.FIELD_1 -> builder.field1(mapper.readValue(p, Field1.class));
        //     case KmipTag.Standard.FIELD_2 -> builder.field2(mapper.readValue(p, Field2.class));
        //     default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        // }
    }

    @Override
    protected Pkcs12CertificateLink build(Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder builder) {
        return builder.build();
    }
}