package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.AttributeValueStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueStructureTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueStructure, AttributeValueStructure.AttributeValueStructureBuilder> {

    public AttributeValueStructureTtlvDeserializer() {
        super(AttributeValueStructure.kmipTag, AttributeValueStructure.encodingType);
    }

    @Override
    protected AttributeValueStructure.AttributeValueStructureBuilder createBuilder() {
        return AttributeValueStructure.builder();
    }

    @Override
    protected void setValue(AttributeValueStructure.AttributeValueStructureBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, KmipDataType.class));
    }

    @Override
    protected AttributeValueStructure build(AttributeValueStructure.AttributeValueStructureBuilder builder) {
        return builder.build();
    }
}