package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KeyMaterial;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.KeyValueStructure;

import java.io.IOException;

public class KeyValueStructureJsonDeserializer extends AbstractKmipStructureJsonDeserializer<KeyValueStructure, KeyValueStructure.KeyValueStructureBuilder> {

    public KeyValueStructureJsonDeserializer() {
        super(KeyValueStructure.kmipTag, KeyValueStructure.encodingType);
    }

    @Override
    protected KeyValueStructure.KeyValueStructureBuilder createBuilder() {
        return KeyValueStructure.builder();
    }

    @Override
    protected void setValue(KeyValueStructure.KeyValueStructureBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_MATERIAL -> builder.keyMaterial(ctxt.readValue(p, KeyMaterial.class));
            default -> builder.attribute(ctxt.readValue(p, KmipAttribute.class));
        }
    }

    @Override
    protected KeyValueStructure build(KeyValueStructure.KeyValueStructureBuilder builder) {
        return builder.build();
    }
}