package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.KeyMaterialStructure;

import java.io.IOException;

public class KeyMaterialStructureXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyMaterialStructure, KeyMaterialStructure.KeyMaterialStructureBuilder> {

    public KeyMaterialStructureXmlDeserializer() {
        super(KeyMaterialStructure.kmipTag, KeyMaterialStructure.encodingType);
    }

    @Override
    protected KeyMaterialStructure.KeyMaterialStructureBuilder createBuilder() {
        return KeyMaterialStructure.builder();
    }

    @Override
    protected void setValue(KeyMaterialStructure.KeyMaterialStructureBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected KeyMaterialStructure build(KeyMaterialStructure.KeyMaterialStructureBuilder builder) {
        return builder.build();
    }
}