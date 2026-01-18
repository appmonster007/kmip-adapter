package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.AttributeValueStructure;

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