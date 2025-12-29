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
import org.purpleBean.kmip.common.ApplicationNamespace;

import java.io.IOException;

public class ApplicationNamespaceXmlDeserializer extends KmipDataTypeXmlDeserializer<ApplicationNamespace> {
    private final KmipTag kmipTag = ApplicationNamespace.kmipTag;
    private final EncodingType encodingType = ApplicationNamespace.encodingType;

    @Override
    public ApplicationNamespace deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ApplicationNamespace.class, "Expected XML object for ApplicationNamespace");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ApplicationNamespace.class, "Invalid Tag for ApplicationNamespace");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ApplicationNamespace.class, "Missing or invalid '@type' attribute for ApplicationNamespace");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ApplicationNamespace.class,
                    "Missing or non-text 'value' for ApplicationNamespace");
            return null;
        }

        String value = valueNode.asText();
        ApplicationNamespace applicationNamespace = ApplicationNamespace.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!applicationNamespace.isSupported()) {
            ctxt.reportInputMismatch(ApplicationNamespace.class, "ApplicationNamespace not supported for spec " + spec);
            return null;
        }

        return applicationNamespace;
    }
}