package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.KeyMaterialStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyMaterialStructureTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<KeyMaterialStructure, KeyMaterialStructure.KeyMaterialStructureBuilder> {

    public KeyMaterialStructureTtlvDeserializer() {
        super(KeyMaterialStructure.kmipTag);
    }

    @Override
    protected KeyMaterialStructure.KeyMaterialStructureBuilder createBuilder() {
        return KeyMaterialStructure.builder();
    }

    @Override
    protected void setValue(KeyMaterialStructure.KeyMaterialStructureBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, KmipDataType.class));
    }

    @Override
    protected KeyMaterialStructure build(KeyMaterialStructure.KeyMaterialStructureBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return KeyMaterialStructure.encodingType;
    }
}