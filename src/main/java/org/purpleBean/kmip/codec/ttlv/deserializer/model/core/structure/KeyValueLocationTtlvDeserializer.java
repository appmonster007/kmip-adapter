package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.model.core.structure.KeyValueLocation;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyValueLocationTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<KeyValueLocation, KeyValueLocation.KeyValueLocationBuilder> {

    public KeyValueLocationTtlvDeserializer() {
        super(KeyValueLocation.kmipTag);
    }

    @Override
    protected KeyValueLocation.KeyValueLocationBuilder createBuilder() {
        return KeyValueLocation.builder();
    }

    @Override
    protected void setValue(KeyValueLocation.KeyValueLocationBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_VALUE_LOCATION_TYPE ->
                    builder.keyValueLocationType(mapper.readValue(p, KeyValueLocationType.class));
            case KmipTag.Standard.KEY_VALUE_LOCATION_VALUE ->
                    builder.keyValueLocationValue(mapper.readValue(p, KeyValueLocationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected KeyValueLocation build(KeyValueLocation.KeyValueLocationBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return KeyValueLocation.encodingType;
    }
}