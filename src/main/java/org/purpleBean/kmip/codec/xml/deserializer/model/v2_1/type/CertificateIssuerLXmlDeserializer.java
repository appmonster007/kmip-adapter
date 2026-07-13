package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerL;

import java.io.IOException;

public class CertificateIssuerLXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateIssuerL, CertificateIssuerL.CertificateIssuerLBuilder> {

    public CertificateIssuerLXmlDeserializer() {
        super(CertificateIssuerL.kmipTag, CertificateIssuerL.encodingType);
    }

    @Override
    protected CertificateIssuerL.CertificateIssuerLBuilder createBuilder() {
        return CertificateIssuerL.builder();
    }

    @Override
    protected void setValue(CertificateIssuerL.CertificateIssuerLBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected CertificateIssuerL build(CertificateIssuerL.CertificateIssuerLBuilder builder) {
        return builder.build();
    }
}