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
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

import java.io.IOException;

public class DataEnumerationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DataEnumeration, DataEnumeration.DataEnumerationBuilder> {

    public DataEnumerationTtlvDeserializer() {
        super(DataEnumeration.kmipTag, DataEnumeration.encodingType);
    }

    @Override
    protected DataEnumeration.DataEnumerationBuilder createBuilder() {
        return DataEnumeration.builder();
    }

    @Override
    protected void setValue(DataEnumeration.DataEnumerationBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(DataEnumeration.fromValue(mapper.readValue(p, Integer.class)));
    }

    @Override
    protected DataEnumeration build(DataEnumeration.DataEnumerationBuilder builder) {
        return builder.build();
    }
}