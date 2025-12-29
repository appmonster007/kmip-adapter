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
import org.purpleBean.kmip.common.ApplicationData;

import java.io.IOException;

public class ApplicationDataXmlDeserializer extends KmipDataTypeXmlDeserializer<ApplicationData> {
    private final KmipTag kmipTag = ApplicationData.kmipTag;
    private final EncodingType encodingType = ApplicationData.encodingType;

    @Override
    public ApplicationData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ApplicationData.class, "Expected XML object for ApplicationData");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ApplicationData.class, "Invalid Tag for ApplicationData");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ApplicationData.class, "Missing or invalid '@type' attribute for ApplicationData");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ApplicationData.class,
                    "Missing or non-text 'value' for ApplicationData");
            return null;
        }

        String value = valueNode.asText();
        ApplicationData applicationData = ApplicationData.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!applicationData.isSupported()) {
            ctxt.reportInputMismatch(ApplicationData.class, "ApplicationData not supported for spec " + spec);
            return null;
        }

        return applicationData;
    }
}