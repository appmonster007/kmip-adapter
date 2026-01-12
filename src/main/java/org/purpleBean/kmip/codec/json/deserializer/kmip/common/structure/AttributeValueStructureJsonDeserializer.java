package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.structure.AttributeValueStructure;

import java.io.IOException;

public class AttributeValueStructureJsonDeserializer extends AbstractKmipStructureJsonDeserializer<AttributeValueStructure, AttributeValueStructure.AttributeValueStructureBuilder> {

    public AttributeValueStructureJsonDeserializer() {
        super(AttributeValueStructure.kmipTag, AttributeValueStructure.encodingType);
    }

    @Override
    protected AttributeValueStructure.AttributeValueStructureBuilder createBuilder() {
        return AttributeValueStructure.builder();
    }

    @Override
    protected void setValue(AttributeValueStructure.AttributeValueStructureBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected AttributeValueStructure build(AttributeValueStructure.AttributeValueStructureBuilder builder) {
        return builder.build();
    }
}