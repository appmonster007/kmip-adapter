package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ClientRegistrationMethod.
 */
public class ClientRegistrationMethodXmlDeserializer extends KmipDataTypeXmlDeserializer<ClientRegistrationMethod> {
    private final KmipTag kmipTag = ClientRegistrationMethod.kmipTag;
    private final EncodingType encodingType = ClientRegistrationMethod.encodingType;

    @Override
    public ClientRegistrationMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, "Expected XML element object for ClientRegistrationMethod");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, "Invalid Tag for ClientRegistrationMethod");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, "Missing or invalid '@type' attribute for ClientRegistrationMethod");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, "Missing or non-text '@value' attribute for ClientRegistrationMethod");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ClientRegistrationMethod clientregistrationmethod = new ClientRegistrationMethod(ClientRegistrationMethod.fromName(description));
        if (!clientregistrationmethod.isSupported()) {
            throw new NoSuchElementException(
                String.format("ClientRegistrationMethod '%s' not supported for spec %s", description, spec));
        }

        return clientregistrationmethod;
    }
}
