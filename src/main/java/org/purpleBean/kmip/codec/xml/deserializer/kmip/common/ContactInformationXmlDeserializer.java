package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ContactInformation;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;

public class ContactInformationXmlDeserializer extends KmipDataTypeXmlDeserializer<ContactInformation> {
    private final KmipTag kmipTag = ContactInformation.kmipTag;
    private final EncodingType encodingType = ContactInformation.encodingType;

    @Override
    public ContactInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ContactInformation.class, "Expected XML object for ContactInformation");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ContactInformation.class, "Invalid Tag for ContactInformation");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ContactInformation.class, "Missing or invalid '@type' attribute for ContactInformation");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ContactInformation.class,
                "Missing or non-text 'value' for ContactInformation");
            return null;
        }

        ContactInformation contactInformation = ContactInformation.builder().value(valueNode.asText()).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!contactInformation.isSupported()) {
            ctxt.reportInputMismatch(ContactInformation.class, "ContactInformation not supported for spec " + spec);
            return null;
        }

        return contactInformation;
    }
}
