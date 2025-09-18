package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for AttestationType.
 */
public class AttestationTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<AttestationType> {

    @Override
    public AttestationType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttestationType.class, "Expected XML element object for AttestationType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttestationType.class, "Missing or invalid '@type' attribute for AttestationType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttestationType.class, "Missing or non-text '@value' attribute for AttestationType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        AttestationType attestationtype = new AttestationType(AttestationType.fromName(spec, description));
        if (!attestationtype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("AttestationType '%s' not supported for spec %s", description, spec));
        }

        return attestationtype;
    }
}
