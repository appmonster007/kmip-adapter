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
import org.purpleBean.kmip.common.ResultMessage;

import java.io.IOException;

public class ResultMessageXmlDeserializer extends KmipDataTypeXmlDeserializer<ResultMessage> {
    private final KmipTag kmipTag = ResultMessage.kmipTag;
    private final EncodingType encodingType = ResultMessage.encodingType;

    @Override
    public ResultMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ResultMessage.class, "Expected XML object for ResultMessage");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ResultMessage.class, "Invalid Tag for ResultMessage");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ResultMessage.class, "Missing or invalid '@type' attribute for ResultMessage");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ResultMessage.class,
                    "Missing or non-text 'value' for ResultMessage");
            return null;
        }

        String value = valueNode.asText();
        ResultMessage resultMessage = ResultMessage.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!resultMessage.isSupported()) {
            ctxt.reportInputMismatch(ResultMessage.class, "ResultMessage not supported for spec " + spec);
            return null;
        }

        return resultMessage;
    }
}