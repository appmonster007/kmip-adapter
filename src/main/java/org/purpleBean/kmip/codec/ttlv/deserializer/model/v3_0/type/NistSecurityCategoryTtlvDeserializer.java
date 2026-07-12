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
import org.purpleBean.kmip.model.v3_0.type.NistSecurityCategory;

import java.io.IOException;

public class NistSecurityCategoryTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NistSecurityCategory, NistSecurityCategory.NistSecurityCategoryBuilder> {

    public NistSecurityCategoryTtlvDeserializer() {
        super(NistSecurityCategory.kmipTag, NistSecurityCategory.encodingType);
    }

    @Override
    protected NistSecurityCategory.NistSecurityCategoryBuilder createBuilder() {
        return NistSecurityCategory.builder();
    }

    @Override
    protected void setValue(NistSecurityCategory.NistSecurityCategoryBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Integer.class));
    }

    @Override
    protected NistSecurityCategory build(NistSecurityCategory.NistSecurityCategoryBuilder builder) {
        return builder.build();
    }
}