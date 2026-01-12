package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.structure.KeyValueLocation;

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