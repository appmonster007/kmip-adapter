package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.structure.OpaqueObject;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.io.IOException;

public class OpaqueObjectJsonDeserializer extends AbstractKmipStructureJsonDeserializer<OpaqueObject, OpaqueObject.OpaqueObjectBuilder> {

    public OpaqueObjectJsonDeserializer() {
        super(OpaqueObject.kmipTag, OpaqueObject.encodingType);
    }

    @Override
    protected OpaqueObject.OpaqueObjectBuilder createBuilder() {
        return OpaqueObject.builder();
    }

    @Override
    protected void setValue(OpaqueObject.OpaqueObjectBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OPAQUE_DATA_TYPE -> builder.opaqueDataType(ctxt.readValue(p, OpaqueDataType.class));
            case KmipTag.Standard.OPAQUE_DATA_VALUE ->
                    builder.opaqueDataValue(ctxt.readValue(p, OpaqueDataValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected OpaqueObject build(OpaqueObject.OpaqueObjectBuilder builder) {
        return builder.build();
    }
}