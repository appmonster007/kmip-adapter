package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute;

import java.io.IOException;

public class CurrentAttributeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CurrentAttribute, CurrentAttribute.CurrentAttributeBuilder> {

    public CurrentAttributeXmlDeserializer() {
        super(CurrentAttribute.kmipTag, CurrentAttribute.encodingType);
    }

    @Override
    protected CurrentAttribute.CurrentAttributeBuilder createBuilder() {
        return CurrentAttribute.builder();
    }

    @Override
    protected void setValue(CurrentAttribute.CurrentAttributeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        JsonNode childNode = ctxt.readTree(p);
        EncodingType childEncodingType = EncodingType.fromName(childNode.get("type").asText())
                .orElseThrow(() -> new IllegalArgumentException("Unknown encoding type in CurrentAttribute child: " + childNode.get("type").asText()));
        Class<? extends KmipDataType> clazz = getKmipDataTypeClass(nodeTag, childEncodingType, ctxt);
        JsonParser childParser = childNode.traverse(p.getCodec());
        childParser.nextToken();
        builder.attribute((KmipAttribute) ctxt.readValue(childParser, clazz));
    }

    @Override
    protected CurrentAttribute build(CurrentAttribute.CurrentAttributeBuilder builder) {
        return builder.build();
    }
}
