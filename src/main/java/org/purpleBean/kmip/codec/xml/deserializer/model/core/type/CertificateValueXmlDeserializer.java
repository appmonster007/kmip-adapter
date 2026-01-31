package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateValue, CertificateValue.CertificateValueBuilder> {

    public CertificateValueXmlDeserializer() {
        super(CertificateValue.kmipTag, CertificateValue.encodingType);
    }

    @Override
    protected CertificateValue.CertificateValueBuilder createBuilder() {
        return CertificateValue.builder();
    }

    @Override
    protected void setValue(CertificateValue.CertificateValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected CertificateValue build(CertificateValue.CertificateValueBuilder builder) {
        return builder.build();
    }
}