package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for DerivationMethod.
 */
public class DerivationMethodXmlDeserializer extends KmipDataTypeXmlDeserializer<DerivationMethod> {

    @Override
    public DerivationMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DerivationMethod.class, "Expected XML element object for DerivationMethod");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DerivationMethod.class, "Missing or invalid '@type' attribute for DerivationMethod");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DerivationMethod.class, "Missing or non-text '@value' attribute for DerivationMethod");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        DerivationMethod derivationmethod = new DerivationMethod(DerivationMethod.fromName(spec, description));
        if (!derivationmethod.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("DerivationMethod '%s' not supported for spec %s", description, spec));
        }

        return derivationmethod;
    }
}
