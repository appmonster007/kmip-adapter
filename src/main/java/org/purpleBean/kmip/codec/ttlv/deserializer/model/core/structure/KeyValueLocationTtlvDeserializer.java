package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.model.core.structure.KeyValueLocation;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyValueLocationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyValueLocation, KeyValueLocation.KeyValueLocationBuilder> {

    public KeyValueLocationTtlvDeserializer() {
        super(KeyValueLocation.kmipTag, KeyValueLocation.encodingType);
    }

    @Override
    protected KeyValueLocation.KeyValueLocationBuilder createBuilder() {
        return KeyValueLocation.builder();
    }

    @Override
    protected void setValue(KeyValueLocation.KeyValueLocationBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}