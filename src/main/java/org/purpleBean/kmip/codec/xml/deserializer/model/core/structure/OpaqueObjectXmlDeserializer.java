package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.structure.OpaqueObject;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.io.IOException;

public class OpaqueObjectXmlDeserializer extends AbstractKmipStructureXmlDeserializer<OpaqueObject, OpaqueObject.OpaqueObjectBuilder> {

    public OpaqueObjectXmlDeserializer() {
        super(OpaqueObject.kmipTag);
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