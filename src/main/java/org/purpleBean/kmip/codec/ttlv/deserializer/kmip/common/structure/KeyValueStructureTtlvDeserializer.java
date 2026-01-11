package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.KeyValueStructure;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class KeyValueStructureTtlvDeserializer extends KmipDataTypeTtlvDeserializer<KeyValueStructure> {
    private final KmipTag kmipTag = KeyValueStructure.kmipTag;
    private final EncodingType encodingType = KeyValueStructure.encodingType;

    @Override
    public KeyValueStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        KeyValueStructure.KeyValueStructureBuilder builder = KeyValueStructure.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        KeyValueStructure keyValueStructure = builder.build();

        if (!keyValueStructure.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", keyValueStructure.getClass().getSimpleName(), spec));
        }
        return keyValueStructure;
    }

    private void setValue(KeyValueStructure.KeyValueStructureBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_MATERIAL ->
                    builder.keyMaterial(mapper.readValue(ttlvObject.toByteBuffer(), KeyMaterial.class));
            default -> builder.attribute(mapper.readValue(ttlvObject.toByteBuffer(), KmipAttribute.class));
        }
    }
}