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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerO;

import java.io.IOException;

public class CertificateIssuerOJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateIssuerO, CertificateIssuerO.CertificateIssuerOBuilder> {

    public CertificateIssuerOJsonDeserializer() {
        super(CertificateIssuerO.kmipTag, CertificateIssuerO.encodingType);
    }

    @Override
    protected CertificateIssuerO.CertificateIssuerOBuilder createBuilder() {
        return CertificateIssuerO.builder();
    }

    @Override
    protected void setValue(CertificateIssuerO.CertificateIssuerOBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected CertificateIssuerO build(CertificateIssuerO.CertificateIssuerOBuilder builder) {
        return builder.build();
    }
}