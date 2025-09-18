package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for CredentialType.
 */
public class CredentialTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<CredentialType> {

    @Override
    public CredentialType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CredentialType.class, "Expected XML element object for CredentialType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
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

        CredentialType credentialtype = new CredentialType(CredentialType.fromName(spec, description));
        if (!credentialtype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("CredentialType '%s' not supported for spec %s", description, spec));
        }

        return credentialtype;
    }
}
