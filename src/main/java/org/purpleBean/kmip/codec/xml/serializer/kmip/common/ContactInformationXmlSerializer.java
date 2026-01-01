package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ContactInformation;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * XML serializer for ContactInformation.
 */
public class ContactInformationXmlSerializer extends KmipDataTypeXmlSerializer<ContactInformation> {

    @Override
    public void serialize(ContactInformation contactInformation, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!contactInformation.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", contactInformation.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = contactInformation.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(contactInformation);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", contactInformation.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", contactInformation.getValue());
        xmlGen.writeEndObject();
    }
}