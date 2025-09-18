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
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for RevocationReasonCode.
 */
public class RevocationReasonCodeXmlDeserializer extends KmipDataTypeXmlDeserializer<RevocationReasonCode> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.REVOCATION_REASON_CODE);

    @Override
    public RevocationReasonCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, "Expected XML element object for RevocationReasonCode");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, "Invalid Tag for RevocationReasonCode");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, "Missing or invalid '@type' attribute for RevocationReasonCode");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, "Missing or non-text '@value' attribute for RevocationReasonCode");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        RevocationReasonCode revocationreasoncode = new RevocationReasonCode(RevocationReasonCode.fromName(spec, description));
        if (!revocationreasoncode.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("RevocationReasonCode '%s' not supported for spec %s", description, spec));
        }

        return revocationreasoncode;
    }
}
