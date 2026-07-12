package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.PasswordSaltAlgorithm;

import java.io.IOException;

public class PasswordSaltAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PasswordSaltAlgorithm, PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder> {

    public PasswordSaltAlgorithmTtlvDeserializer() {
        super(PasswordSaltAlgorithm.kmipTag, PasswordSaltAlgorithm.encodingType);
    }

    @Override
    protected PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder createBuilder() {
        return PasswordSaltAlgorithm.builder();
    }

    @Override
    protected void setValue(PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(PasswordSaltAlgorithm.fromValue(mapper.readValue(p, Integer.class)));
    }

    @Override
    protected PasswordSaltAlgorithm build(PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder builder) {
        return builder.build();
    }
}