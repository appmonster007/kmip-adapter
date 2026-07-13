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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerL;

import java.io.IOException;

public class CertificateIssuerLTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerL, CertificateIssuerL.CertificateIssuerLBuilder> {

    public CertificateIssuerLTtlvDeserializer() {
        super(CertificateIssuerL.kmipTag, CertificateIssuerL.encodingType);
    }

    @Override
    protected CertificateIssuerL.CertificateIssuerLBuilder createBuilder() {
        return CertificateIssuerL.builder();
    }

    @Override
    protected void setValue(CertificateIssuerL.CertificateIssuerLBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected CertificateIssuerL build(CertificateIssuerL.CertificateIssuerLBuilder builder) {
        return builder.build();
    }
}