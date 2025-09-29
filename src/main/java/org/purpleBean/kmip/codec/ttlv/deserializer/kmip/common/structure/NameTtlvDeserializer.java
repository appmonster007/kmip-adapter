package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.common.structure.Name;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class NameTtlvDeserializer extends KmipDataTypeTtlvDeserializer<Name> {
    private final KmipTag kmipTag = Name.kmipTag;
    private final EncodingType encodingType = Name.encodingType;

    @Override
    public Name deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject nodeTtlvObject = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(nodeTtlvObject.getTag(), kmipTag.getTagBytes()) && nodeTtlvObject.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), nodeTtlvObject.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(nodeTtlvObject.getValue());
        KmipSpec spec = KmipContext.getSpec();
        Name.NameBuilder builder = Name.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        Name name = builder.build();

        if (!name.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", name.getClass().getSimpleName(), spec));
        }
        return name;
    }

    private void setValue(Name.NameBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.NAME_VALUE ->
                    builder.nameValue(mapper.readValue(ttlvObject.toByteBuffer(), NameValue.class));
            case KmipTag.Standard.NAME_TYPE ->
                    builder.nameType(mapper.readValue(ttlvObject.toByteBuffer(), NameType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
