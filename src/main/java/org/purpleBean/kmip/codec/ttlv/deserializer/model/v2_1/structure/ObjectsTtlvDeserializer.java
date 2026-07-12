package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.Objects;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObjectsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Objects, Objects.ObjectsBuilder> {

    public ObjectsTtlvDeserializer() {
        super(Objects.kmipTag, Objects.encodingType);
    }

    @Override
    protected Objects.ObjectsBuilder createBuilder() {
        return Objects.builder();
    }

    @Override
    protected void setValue(Objects.ObjectsBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, org.purpleBean.kmip.model.core.type.UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Objects build(Objects.ObjectsBuilder builder) {
        return builder.build();
    }
}