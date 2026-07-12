package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

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
import org.purpleBean.kmip.model.core.type.ShortUniqueIdentifier;

import java.io.IOException;

public class ShortUniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ShortUniqueIdentifier, ShortUniqueIdentifier.ShortUniqueIdentifierBuilder> {

    public ShortUniqueIdentifierTtlvDeserializer() {
        super(ShortUniqueIdentifier.kmipTag, ShortUniqueIdentifier.encodingType);
    }

    @Override
    protected ShortUniqueIdentifier.ShortUniqueIdentifierBuilder createBuilder() {
        return ShortUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(ShortUniqueIdentifier.ShortUniqueIdentifierBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected ShortUniqueIdentifier build(ShortUniqueIdentifier.ShortUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}