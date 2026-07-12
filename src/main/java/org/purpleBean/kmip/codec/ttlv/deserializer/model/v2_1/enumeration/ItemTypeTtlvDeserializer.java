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
import org.purpleBean.kmip.model.v2_1.enumeration.ItemType;

import java.io.IOException;

public class ItemTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ItemType, ItemType.ItemTypeBuilder> {

    public ItemTypeTtlvDeserializer() {
        super(ItemType.kmipTag, ItemType.encodingType);
    }

    @Override
    protected ItemType.ItemTypeBuilder createBuilder() {
        return ItemType.builder();
    }

    @Override
    protected void setValue(ItemType.ItemTypeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(ItemType.fromValue(mapper.readValue(p, Integer.class)));
    }

    @Override
    protected ItemType build(ItemType.ItemTypeBuilder builder) {
        return builder.build();
    }
}