package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyMaterialStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyMaterialStructureTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyMaterialStructure, KeyMaterialStructure.KeyMaterialStructureBuilder> {

    public KeyMaterialStructureTtlvDeserializer() {
        super(KeyMaterialStructure.kmipTag, KeyMaterialStructure.encodingType);
    }

    @Override
    protected KeyMaterialStructure.KeyMaterialStructureBuilder createBuilder() {
        return KeyMaterialStructure.builder();
    }

    @Override
    protected void setValue(KeyMaterialStructure.KeyMaterialStructureBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, KmipDataType.class));
    }

    @Override
    protected KeyMaterialStructure build(KeyMaterialStructure.KeyMaterialStructureBuilder builder) {
        return builder.build();
    }
}