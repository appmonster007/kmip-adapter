package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.AttributeValueStructure;

import java.io.IOException;

public class AttributeValueStructureXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueStructure, AttributeValueStructure.AttributeValueStructureBuilder> {

    public AttributeValueStructureXmlDeserializer() {
        super(AttributeValueStructure.kmipTag, AttributeValueStructure.encodingType);
    }

    @Override
    protected AttributeValueStructure.AttributeValueStructureBuilder createBuilder() {
        return AttributeValueStructure.builder();
    }

    @Override
    protected void setValue(AttributeValueStructure.AttributeValueStructureBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);

        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected AttributeValueStructure build(AttributeValueStructure.AttributeValueStructureBuilder builder) {
        return builder.build();
    }
}