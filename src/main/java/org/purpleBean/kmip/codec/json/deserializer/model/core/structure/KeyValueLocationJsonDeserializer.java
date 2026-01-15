package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.model.core.structure.KeyValueLocation;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

import java.io.IOException;

public class KeyValueLocationJsonDeserializer extends AbstractKmipStructureJsonDeserializer<KeyValueLocation, KeyValueLocation.KeyValueLocationBuilder> {

    public KeyValueLocationJsonDeserializer() {
        super(KeyValueLocation.kmipTag, KeyValueLocation.encodingType);
    }

    @Override
    protected KeyValueLocation.KeyValueLocationBuilder createBuilder() {
        return KeyValueLocation.builder();
    }

    @Override
    protected void setValue(KeyValueLocation.KeyValueLocationBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_VALUE_LOCATION_TYPE ->
                    builder.keyValueLocationType(ctxt.readValue(p, KeyValueLocationType.class));
            case KmipTag.Standard.KEY_VALUE_LOCATION_VALUE ->
                    builder.keyValueLocationValue(ctxt.readValue(p, KeyValueLocationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected KeyValueLocation build(KeyValueLocation.KeyValueLocationBuilder builder) {
        return builder.build();
    }
}