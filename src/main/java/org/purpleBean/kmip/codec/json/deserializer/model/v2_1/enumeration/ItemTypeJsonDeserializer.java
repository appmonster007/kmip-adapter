package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.ItemType;

import java.io.IOException;

public class ItemTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ItemType, ItemType.ItemTypeBuilder> {

    public ItemTypeJsonDeserializer() {
        super(ItemType.kmipTag, ItemType.encodingType);
    }

    @Override
    protected ItemType.ItemTypeBuilder createBuilder() {
        return ItemType.builder();
    }

    @Override
    protected void setValue(ItemType.ItemTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ItemType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ItemType build(ItemType.ItemTypeBuilder builder) {
        return builder.build();
    }
}