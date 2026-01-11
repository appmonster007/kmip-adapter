package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ContactInformation;

import java.io.IOException;

/**
 * XML deserializer for ContactInformation.
 */
public class ContactInformationXmlDeserializer extends KmipDataTypeXmlDeserializer<ContactInformation> {
    private final KmipTag kmipTag = ContactInformation.kmipTag;
    private final EncodingType encodingType = ContactInformation.encodingType;

    @Override
    public ContactInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(ContactInformation.class, "Invalid Tag for ContactInformation");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        ContactInformation.ContactInformationBuilder builder = ContactInformation.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(ContactInformation.class, "Missing or invalid 'type' attribute for ContactInformation");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(ContactInformation.class,
                                "Missing or non-text 'value' for ContactInformation");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        ContactInformation contactInformation = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!contactInformation.isSupported()) {
            ctxt.reportInputMismatch(ContactInformation.class, "ContactInformation not supported for spec " + spec);
            return null;
        }

        return contactInformation;
    }
}