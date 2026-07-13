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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerSt;

import java.io.IOException;

public class CertificateIssuerStJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateIssuerSt, CertificateIssuerSt.CertificateIssuerStBuilder> {

    public CertificateIssuerStJsonDeserializer() {
        super(CertificateIssuerSt.kmipTag, CertificateIssuerSt.encodingType);
    }

    @Override
    protected CertificateIssuerSt.CertificateIssuerStBuilder createBuilder() {
        return CertificateIssuerSt.builder();
    }

    @Override
    protected void setValue(CertificateIssuerSt.CertificateIssuerStBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected CertificateIssuerSt build(CertificateIssuerSt.CertificateIssuerStBuilder builder) {
        return builder.build();
    }
}