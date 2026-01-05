package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.structure.KeyValueLocation;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class KeyValueLocationTtlvDeserializer extends KmipDataTypeTtlvDeserializer<KeyValueLocation> {
    private final KmipTag kmipTag = KeyValueLocation.kmipTag;
    private final EncodingType encodingType = KeyValueLocation.encodingType;

    @Override
    public KeyValueLocation deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        KeyValueLocation.KeyValueLocationBuilder builder = KeyValueLocation.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        KeyValueLocation keyvaluelocation = builder.build();
        if (!keyvaluelocation.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", keyvaluelocation.getClass().getSimpleName(), spec));
        }
        return keyvaluelocation;
    }

    private void setValue(
            KeyValueLocation.KeyValueLocationBuilder builder,
            KmipTag.Value nodeTag,
            TtlvObject ttlvObject,
            TtlvMapper mapper
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_VALUE_LOCATION_TYPE ->
                    builder.keyValueLocationType(mapper.readValue(ttlvObject.toByteBuffer(), KeyValueLocationType.class));
            case KmipTag.Standard.KEY_VALUE_LOCATION_VALUE ->
                    builder.keyValueLocationValue(mapper.readValue(ttlvObject.toByteBuffer(), KeyValueLocationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}