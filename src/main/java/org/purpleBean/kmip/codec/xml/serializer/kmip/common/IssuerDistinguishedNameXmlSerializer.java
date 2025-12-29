package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IssuerDistinguishedNameXmlSerializer extends KmipDataTypeXmlSerializer<IssuerDistinguishedName> {

    @Override
    public void serialize(IssuerDistinguishedName issuerDistinguishedName, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!issuerDistinguishedName.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", issuerDistinguishedName.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = issuerDistinguishedName.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(issuerDistinguishedName);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", issuerDistinguishedName.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", issuerDistinguishedName.getValue());
        xmlGen.writeEndObject();
    }
}
