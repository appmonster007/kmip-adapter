package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;

import java.io.IOException;
import java.util.NoSuchElementException;

public class KmipAttributeJsonDeserializer<T extends KmipAttribute> extends KmipDataTypeJsonDeserializer<KmipAttribute> {

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode tagNode = node.get("tag");
        JsonNode typeNode = node.get("type");

        if (tagNode == null || typeNode == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        KmipTag.Value kmipTagValue = KmipTag.fromName(spec, tagNode.asText());
        EncodingType encodingType = EncodingType.fromName(typeNode.asText()).orElse(null);

        if (kmipTagValue == null || encodingType == null) {
            return null;
        }

        Class<? extends KmipAttribute> attributeClass = KmipAttribute.getClassFromRegistry(kmipTagValue, encodingType);
        if (attributeClass == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTagValue.getValue(), encodingType));
        }

        return (T) p.getCodec().treeToValue(node, attributeClass);
    }
}