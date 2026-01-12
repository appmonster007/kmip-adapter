package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KeyMaterial;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.KeyValueStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyValueStructureTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<KeyValueStructure, KeyValueStructure.KeyValueStructureBuilder> {

    public KeyValueStructureTtlvDeserializer() {
        super(KeyValueStructure.kmipTag);
    }

    @Override
    protected KeyValueStructure.KeyValueStructureBuilder createBuilder() {
        return KeyValueStructure.builder();
    }

    @Override
    protected void setValue(KeyValueStructure.KeyValueStructureBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_MATERIAL -> builder.keyMaterial(mapper.readValue(p, KeyMaterial.class));
            default -> builder.attribute(mapper.readValue(p, KmipAttribute.class));
        }
    }

    @Override
    protected KeyValueStructure build(KeyValueStructure.KeyValueStructureBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return KeyValueStructure.encodingType;
    }
}