package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

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
import org.purpleBean.kmip.model.core.enumeration.UniqueIdentifier;

import java.io.IOException;

public class UniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UniqueIdentifier, UniqueIdentifier.UniqueIdentifierBuilder> {

    public UniqueIdentifierTtlvDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
    }

    @Override
    protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
        return UniqueIdentifier.builder();
    }

    @Override
    protected void setValue(UniqueIdentifier.UniqueIdentifierBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(UniqueIdentifier.fromValue(mapper.readValue(p, Integer.class)));
    }

    @Override
    protected UniqueIdentifier build(UniqueIdentifier.UniqueIdentifierBuilder builder) {
        return builder.build();
    }
}