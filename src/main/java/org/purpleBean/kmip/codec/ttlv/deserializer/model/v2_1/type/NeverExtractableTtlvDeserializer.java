package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.NeverExtractable;

import java.io.IOException;

public class NeverExtractableTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NeverExtractable, NeverExtractable.NeverExtractableBuilder> {

    public NeverExtractableTtlvDeserializer() {
        super(NeverExtractable.kmipTag, NeverExtractable.encodingType);
    }

    @Override
    protected NeverExtractable.NeverExtractableBuilder createBuilder() {
        return NeverExtractable.builder();
    }

    @Override
    protected void setValue(NeverExtractable.NeverExtractableBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Boolean.class));
    }

    @Override
    protected NeverExtractable build(NeverExtractable.NeverExtractableBuilder builder) {
        return builder.build();
    }
}