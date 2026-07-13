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
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectEmail;

import java.io.IOException;

public class CertificateSubjectEmailTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectEmail, CertificateSubjectEmail.CertificateSubjectEmailBuilder> {

    public CertificateSubjectEmailTtlvDeserializer() {
        super(CertificateSubjectEmail.kmipTag, CertificateSubjectEmail.encodingType);
    }

    @Override
    protected CertificateSubjectEmail.CertificateSubjectEmailBuilder createBuilder() {
        return CertificateSubjectEmail.builder();
    }

    @Override
    protected void setValue(CertificateSubjectEmail.CertificateSubjectEmailBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected CertificateSubjectEmail build(CertificateSubjectEmail.CertificateSubjectEmailBuilder builder) {
        return builder.build();
    }
}