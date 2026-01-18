package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.structure.OpaqueObject;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OpaqueObjectTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<OpaqueObject, OpaqueObject.OpaqueObjectBuilder> {

    public OpaqueObjectTtlvDeserializer() {
        super(OpaqueObject.kmipTag);
    }

    @Override
    protected OpaqueObject.OpaqueObjectBuilder createBuilder() {
        return OpaqueObject.builder();
    }

    @Override
    protected void setValue(OpaqueObject.OpaqueObjectBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OPAQUE_DATA_TYPE -> builder.opaqueDataType(mapper.readValue(p, OpaqueDataType.class));
            case KmipTag.Standard.OPAQUE_DATA_VALUE ->
                    builder.opaqueDataValue(mapper.readValue(p, OpaqueDataValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected OpaqueObject build(OpaqueObject.OpaqueObjectBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return OpaqueObject.encodingType;
    }
}