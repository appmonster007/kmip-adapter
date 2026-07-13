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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerSt;

import java.io.IOException;

public class CertificateIssuerStTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerSt, CertificateIssuerSt.CertificateIssuerStBuilder> {

    public CertificateIssuerStTtlvDeserializer() {
        super(CertificateIssuerSt.kmipTag, CertificateIssuerSt.encodingType);
    }

    @Override
    protected CertificateIssuerSt.CertificateIssuerStBuilder createBuilder() {
        return CertificateIssuerSt.builder();
    }

    @Override
    protected void setValue(CertificateIssuerSt.CertificateIssuerStBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected CertificateIssuerSt build(CertificateIssuerSt.CertificateIssuerStBuilder builder) {
        return builder.build();
    }
}