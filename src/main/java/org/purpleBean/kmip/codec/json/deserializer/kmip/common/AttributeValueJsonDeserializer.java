package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;

/**
 * JSON deserializer for AttributeValue.
 */
public class AttributeValueJsonDeserializer extends KmipDataTypeJsonDeserializer<Attribute.AttributeValue> {

    @Override
    public Attribute.AttributeValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipSpec spec = KmipContext.getSpec();
        KmipAttribute kmipAttribute;
        String name = node.get("name").asText();

        if (Attribute.isCustomAttribute(name)) {
            kmipAttribute = p.getCodec().treeToValue(node, Attribute.CustomAttribute.class);
        } else {
            KmipTag.Value kmipTagValue = KmipTag.fromName(spec, name);
            EncodingType encodingType = EncodingType.fromName(node.get("type").asText()).get();
            Class<?> attrClass = KmipAttribute.getClassFromRegistry(spec, kmipTagValue, encodingType);

            ObjectNode attrValueNodeTag = (ObjectNode) node;
            attrValueNodeTag.put("tag", kmipTagValue.getDescription());

            kmipAttribute = (KmipAttribute) p.getCodec().treeToValue(attrValueNodeTag, attrClass);
        }

        return Attribute.AttributeValue.of(kmipAttribute);
    }
}
