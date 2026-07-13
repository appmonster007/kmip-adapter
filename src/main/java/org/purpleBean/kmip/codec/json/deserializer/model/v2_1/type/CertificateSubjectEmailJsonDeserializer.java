package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectEmail;

import java.io.IOException;

public class CertificateSubjectEmailJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateSubjectEmail, CertificateSubjectEmail.CertificateSubjectEmailBuilder> {

    public CertificateSubjectEmailJsonDeserializer() {
        super(CertificateSubjectEmail.kmipTag, CertificateSubjectEmail.encodingType);
    }

    @Override
    protected CertificateSubjectEmail.CertificateSubjectEmailBuilder createBuilder() {
        return CertificateSubjectEmail.builder();
    }

    @Override
    protected void setValue(CertificateSubjectEmail.CertificateSubjectEmailBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected CertificateSubjectEmail build(CertificateSubjectEmail.CertificateSubjectEmailBuilder builder) {
        return builder.build();
    }
}