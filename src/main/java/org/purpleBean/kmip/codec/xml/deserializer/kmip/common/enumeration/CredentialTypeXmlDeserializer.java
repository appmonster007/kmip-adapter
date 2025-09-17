package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

import java.io.IOException;
import java.util.NoSuchElementException;

public class CredentialTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<CredentialType> {

    @Override
    public CredentialType deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(CredentialType.class, "Expected XML element object for CredentialType");
            return null;
        }
        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CredentialType.class, "Missing or invalid '@type' attribute for CredentialType");
            return null;
        }
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CredentialType.class, "Missing or non-text '@value' attribute for CredentialType");
            return null;
        }
        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();
        CredentialType.Value v;
        try {
            v = CredentialType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(CredentialType.class, "Unknown value '" + description + "' for spec " + spec);
            return null;
        }
        CredentialType result = new CredentialType(v);
        if (!result.isSupportedFor(spec)) {
            throw new NoSuchElementException("CredentialType '" + description + "' not supported for spec " + spec);
        }
        return result;
    }
}
