package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.AttributeValueStructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class AttributeValueStructureXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueStructure> {
    private final KmipTag kmipTag = AttributeValueStructure.kmipTag;
    private final EncodingType encodingType = AttributeValueStructure.encodingType;

    @Override
    public AttributeValueStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, "Expected XML object for AttributeValue.Structure");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, "Invalid Tag for AttributeValue.Structure");
            return null;
        }

        List<KmipDataType> values = new ArrayList<>();
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            if (entry.getValue().isObject()) {
                values.add(deserializeObjects(entry.getKey(), entry.getValue(), p, ctxt));
            } else if (entry.getValue().isArray()) {
                var nestedFields = entry.getValue().values();
                while (nestedFields.hasNext()) {
                    JsonNode nestedField = nestedFields.next();
                    if (nestedField.isObject()) {
                        values.add(deserializeObjects(entry.getKey(), nestedField, p, ctxt));
                    }
                }
            }
        }
        AttributeValueStructure attributeValueStructure = AttributeValueStructure.of(values);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueStructure.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, "AttributeValue.Structure not supported for spec " + spec);
            return null;
        }

        return attributeValueStructure;
    }

    private KmipDataType deserializeObjects(String nodeName, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (!node.has("type") || !node.has("value")) {
            ctxt.reportInputMismatch(AttributeValue.class, "Missing 'type', or 'value' field in JSON");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipSpec spec = KmipContext.getSpec();
        KmipTag tag;
        try {
            tag = new KmipTag(KmipTag.fromName(spec, nodeName));
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValue.class, String.format("Failed to parse KMIP tag for AttributeValue: %s", e.getMessage()));
            return null;
        }


        if (!node.has("type") && !node.get("type").isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Invalid 'type' field in JSON");
            return null;
        }
        String type = node.get("type").asText();
        EncodingType encodingType = EncodingType.fromName(type).get();

        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(tag.getValue(), encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", tag.getValue(), encodingType));
        }

        return p.getCodec().treeToValue(node, clazz);
    }
}
