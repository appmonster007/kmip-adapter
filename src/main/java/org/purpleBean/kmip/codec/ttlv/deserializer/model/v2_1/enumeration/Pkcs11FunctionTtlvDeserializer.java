package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.enumeration;

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
import org.purpleBean.kmip.model.v2_1.enumeration.Pkcs11Function;

import java.io.IOException;

public class Pkcs11FunctionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Pkcs11Function, Pkcs11Function.Pkcs11FunctionBuilder> {

    public Pkcs11FunctionTtlvDeserializer() {
        super(Pkcs11Function.kmipTag, Pkcs11Function.encodingType);
    }

    @Override
    protected Pkcs11Function.Pkcs11FunctionBuilder createBuilder() {
        return Pkcs11Function.builder();
    }

    @Override
    protected void setValue(Pkcs11Function.Pkcs11FunctionBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(Pkcs11Function.fromValue(mapper.readValue(p, Integer.class)));
    }

    @Override
    protected Pkcs11Function build(Pkcs11Function.Pkcs11FunctionBuilder builder) {
        return builder.build();
    }
}