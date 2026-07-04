package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;

import java.io.IOException;

public class NewAttributeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NewAttribute, NewAttribute.NewAttributeBuilder> {

    public NewAttributeJsonDeserializer() {
        super(NewAttribute.kmipTag, NewAttribute.encodingType);
    }

    @Override
    protected NewAttribute.NewAttributeBuilder createBuilder() {
        return NewAttribute.builder();
    }

    @Override
    protected void setValue(NewAttribute.NewAttributeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        JsonNode childNode = ctxt.readTree(p);
        EncodingType childEncodingType = EncodingType.fromName(childNode.get("type").asText())
                .orElseThrow(() -> new IllegalArgumentException("Unknown encoding type in NewAttribute child: " + childNode.get("type").asText()));
        Class<? extends KmipDataType> clazz = getKmipDataTypeClass(nodeTag, childEncodingType, ctxt);
        JsonParser childParser = childNode.traverse(p.getCodec());
        childParser.nextToken();
        builder.attribute((KmipAttribute) ctxt.readValue(childParser, clazz));
    }

    @Override
    protected NewAttribute build(NewAttribute.NewAttributeBuilder builder) {
        return builder.build();
    }
}
