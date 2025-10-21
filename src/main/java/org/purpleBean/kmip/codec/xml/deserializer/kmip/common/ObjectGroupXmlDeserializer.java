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
import org.purpleBean.kmip.common.ObjectGroup;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;

public class ObjectGroupXmlDeserializer extends KmipDataTypeXmlDeserializer<ObjectGroup> {
    private final KmipTag kmipTag = ObjectGroup.kmipTag;
    private final EncodingType encodingType = ObjectGroup.encodingType;

    @Override
    public ObjectGroup deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ObjectGroup.class, "Expected XML object for ObjectGroup");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ObjectGroup.class, "Invalid Tag for ObjectGroup");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ObjectGroup.class, "Missing or invalid '@type' attribute for ObjectGroup");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ObjectGroup.class,
                "Missing or non-text 'value' for ObjectGroup");
            return null;
        }

        ObjectGroup objectGroup = ObjectGroup.builder().value(valueNode.asText()).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!objectGroup.isSupported()) {
            ctxt.reportInputMismatch(ObjectGroup.class, "ObjectGroup not supported for spec " + spec);
            return null;
        }

        return objectGroup;
    }
}
