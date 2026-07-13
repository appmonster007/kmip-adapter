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
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectSt;

import java.io.IOException;

public class CertificateSubjectStXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateSubjectSt, CertificateSubjectSt.CertificateSubjectStBuilder> {

    public CertificateSubjectStXmlDeserializer() {
        super(CertificateSubjectSt.kmipTag, CertificateSubjectSt.encodingType);
    }

    @Override
    protected CertificateSubjectSt.CertificateSubjectStBuilder createBuilder() {
        return CertificateSubjectSt.builder();
    }

    @Override
    protected void setValue(CertificateSubjectSt.CertificateSubjectStBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected CertificateSubjectSt build(CertificateSubjectSt.CertificateSubjectStBuilder builder) {
        return builder.build();
    }
}