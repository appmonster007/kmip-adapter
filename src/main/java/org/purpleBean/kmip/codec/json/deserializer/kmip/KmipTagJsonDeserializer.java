package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.io.IOException;

public class KmipTagJsonDeserializer extends JsonDeserializer<KmipTag> {
    @Override
    public KmipTag deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SampleStructure.class, "Expected object for KmipTag");
            return null;
        }

        JsonNode nameNode = node.get("name");
        JsonNode tagNode = node.get("tag");

        String value = null;
        if (nameNode != null && nameNode.isTextual()) {
            value = nameNode.asText();
        } else if (tagNode != null && tagNode.isTextual()) {
            value = tagNode.asText();
        }

        if (value == null) {
            ctxt.reportInputMismatch(KmipTag.class,
                    "Expected 'name' or 'tag' field with string value in object");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        return new KmipTag(KmipTag.fromName(spec, value));
    }

}
