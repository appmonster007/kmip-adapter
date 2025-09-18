package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for CancellationResult.
 */
public class CancellationResultXmlDeserializer extends KmipDataTypeXmlDeserializer<CancellationResult> {

    @Override
    public CancellationResult deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CancellationResult.class, "Expected XML element object for CancellationResult");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CancellationResult.class, "Missing or invalid '@type' attribute for CancellationResult");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CancellationResult.class, "Missing or non-text '@value' attribute for CancellationResult");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        CancellationResult cancellationresult = new CancellationResult(CancellationResult.fromName(spec, description));
        if (!cancellationresult.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("CancellationResult '%s' not supported for spec %s", description, spec));
        }

        return cancellationresult;
    }
}
