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
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ProcessingStage.
 */
public class ProcessingStageXmlDeserializer extends KmipDataTypeXmlDeserializer<ProcessingStage> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROCESSING_STAGE);

    @Override
    public ProcessingStage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ProcessingStage.class, "Expected XML element object for ProcessingStage");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ProcessingStage.class, "Invalid Tag for ProcessingStage");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ProcessingStage.class, "Missing or invalid '@type' attribute for ProcessingStage");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ProcessingStage.class, "Missing or non-text '@value' attribute for ProcessingStage");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ProcessingStage processingstage = new ProcessingStage(ProcessingStage.fromName(spec, description));
        if (!processingstage.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("ProcessingStage '%s' not supported for spec %s", description, spec));
        }

        return processingstage;
    }
}
