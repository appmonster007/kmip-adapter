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
import org.purpleBean.kmip.model.v2_1.type.Extractable;

import java.io.IOException;

public class ExtractableTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Extractable, Extractable.ExtractableBuilder> {

    public ExtractableTtlvDeserializer() {
        super(Extractable.kmipTag, Extractable.encodingType);
    }

    @Override
    protected Extractable.ExtractableBuilder createBuilder() {
        return Extractable.builder();
    }

    @Override
    protected void setValue(Extractable.ExtractableBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Boolean.class));
    }

    @Override
    protected Extractable build(Extractable.ExtractableBuilder builder) {
        return builder.build();
    }
}