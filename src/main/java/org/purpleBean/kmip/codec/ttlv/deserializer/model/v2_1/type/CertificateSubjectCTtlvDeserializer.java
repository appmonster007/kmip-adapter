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
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectC;

import java.io.IOException;

public class CertificateSubjectCTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectC, CertificateSubjectC.CertificateSubjectCBuilder> {

    public CertificateSubjectCTtlvDeserializer() {
        super(CertificateSubjectC.kmipTag, CertificateSubjectC.encodingType);
    }

    @Override
    protected CertificateSubjectC.CertificateSubjectCBuilder createBuilder() {
        return CertificateSubjectC.builder();
    }

    @Override
    protected void setValue(CertificateSubjectC.CertificateSubjectCBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected CertificateSubjectC build(CertificateSubjectC.CertificateSubjectCBuilder builder) {
        return builder.build();
    }
}