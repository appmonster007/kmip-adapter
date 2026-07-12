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
import org.purpleBean.kmip.model.core.type.Pkcs12FriendlyName;

import java.io.IOException;

public class Pkcs12FriendlyNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Pkcs12FriendlyName, Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder> {

    public Pkcs12FriendlyNameTtlvDeserializer() {
        super(Pkcs12FriendlyName.kmipTag, Pkcs12FriendlyName.encodingType);
    }

    @Override
    protected Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder createBuilder() {
        return Pkcs12FriendlyName.builder();
    }

    @Override
    protected void setValue(Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected Pkcs12FriendlyName build(Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder builder) {
        return builder.build();
    }
}