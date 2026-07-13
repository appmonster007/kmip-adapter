package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerC;

import java.io.IOException;

public class CertificateIssuerCTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerC, CertificateIssuerC.CertificateIssuerCBuilder> {

    public CertificateIssuerCTtlvDeserializer() {
        super(CertificateIssuerC.kmipTag, CertificateIssuerC.encodingType);
    }

    @Override
    protected CertificateIssuerC.CertificateIssuerCBuilder createBuilder() {
        return CertificateIssuerC.builder();
    }

    @Override
    protected void setValue(CertificateIssuerC.CertificateIssuerCBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected CertificateIssuerC build(CertificateIssuerC.CertificateIssuerCBuilder builder) {
        return builder.build();
    }
}