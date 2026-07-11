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
import org.purpleBean.kmip.model.core.type.Pkcs11OutputParameters;

import java.io.IOException;

public class Pkcs11OutputParametersTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Pkcs11OutputParameters, Pkcs11OutputParameters.Pkcs11OutputParametersBuilder> {

    public Pkcs11OutputParametersTtlvDeserializer() {
        super(Pkcs11OutputParameters.kmipTag, Pkcs11OutputParameters.encodingType);
    }

    @Override
    protected Pkcs11OutputParameters.Pkcs11OutputParametersBuilder createBuilder() {
        return Pkcs11OutputParameters.builder();
    }

    @Override
    protected void setValue(Pkcs11OutputParameters.Pkcs11OutputParametersBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected Pkcs11OutputParameters build(Pkcs11OutputParameters.Pkcs11OutputParametersBuilder builder) {
        return builder.build();
    }
}