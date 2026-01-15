package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KeyMaterial;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyValueStructure;

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