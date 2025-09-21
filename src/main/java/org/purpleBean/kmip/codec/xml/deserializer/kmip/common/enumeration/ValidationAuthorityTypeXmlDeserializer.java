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
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ValidationAuthorityType.
 */
public class ValidationAuthorityTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<ValidationAuthorityType> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.VALIDATION_AUTHORITY_TYPE);

    @Override
    public ValidationAuthorityType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, "Expected XML element object for ValidationAuthorityType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, "Invalid Tag for ValidationAuthorityType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, "Missing or invalid '@type' attribute for ValidationAuthorityType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, "Missing or non-text '@value' attribute for ValidationAuthorityType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ValidationAuthorityType validationauthoritytype = new ValidationAuthorityType(ValidationAuthorityType.fromName(spec, description));
        if (!validationauthoritytype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("ValidationAuthorityType '%s' not supported for spec %s", description, spec));
        }

        return validationauthoritytype;
    }
}
