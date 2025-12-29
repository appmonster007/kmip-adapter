package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

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
import org.purpleBean.kmip.common.BatchOrderOption;

import java.io.IOException;

public class BatchOrderOptionXmlDeserializer extends KmipDataTypeXmlDeserializer<BatchOrderOption> {
    private final KmipTag kmipTag = BatchOrderOption.kmipTag;
    private final EncodingType encodingType = BatchOrderOption.encodingType;

    @Override
    public BatchOrderOption deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "Expected XML object for BatchOrderOption");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "Invalid Tag for BatchOrderOption");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "Missing or invalid '@type' attribute for BatchOrderOption");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(BatchOrderOption.class,
                    "Missing or non-boolean 'value' for BatchOrderOption");
            return null;
        }

        boolean value = Boolean.parseBoolean(valueNode.asText());
        BatchOrderOption batchOrderOption = BatchOrderOption.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!batchOrderOption.isSupported()) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "BatchOrderOption not supported for spec " + spec);
            return null;
        }

        return batchOrderOption;
    }
}