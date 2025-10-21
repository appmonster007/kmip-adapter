package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.OperationPolicyName;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;

public class OperationPolicyNameXmlDeserializer extends KmipDataTypeXmlDeserializer<OperationPolicyName> {
    private final KmipTag kmipTag = OperationPolicyName.kmipTag;
    private final EncodingType encodingType = OperationPolicyName.encodingType;

    @Override
    public OperationPolicyName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(OperationPolicyName.class, "Expected XML object for OperationPolicyName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(OperationPolicyName.class, "Invalid Tag for OperationPolicyName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(OperationPolicyName.class, "Missing or invalid '@type' attribute for OperationPolicyName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(OperationPolicyName.class,
                "Missing or non-text 'value' for OperationPolicyName");
            return null;
        }

        String value = valueNode.asText();
        OperationPolicyName operationPolicyName = OperationPolicyName.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!operationPolicyName.isSupported()) {
            ctxt.reportInputMismatch(OperationPolicyName.class, "OperationPolicyName not supported for spec " + spec);
            return null;
        }

        return operationPolicyName;
    }
}
