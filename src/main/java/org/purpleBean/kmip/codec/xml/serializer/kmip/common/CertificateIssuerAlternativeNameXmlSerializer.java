package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CertificateIssuerAlternativeNameXmlSerializer extends KmipDataTypeXmlSerializer<CertificateIssuerAlternativeName> {

    @Override
    public void serialize(CertificateIssuerAlternativeName certificateIssuerAlternativeName, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateIssuerAlternativeName.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", certificateIssuerAlternativeName.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = certificateIssuerAlternativeName.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(certificateIssuerAlternativeName);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", certificateIssuerAlternativeName.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", certificateIssuerAlternativeName.getValue());
        xmlGen.writeEndObject();
    }
}