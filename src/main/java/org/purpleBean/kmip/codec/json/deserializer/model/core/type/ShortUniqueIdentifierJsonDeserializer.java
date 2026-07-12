package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

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
import org.purpleBean.kmip.model.core.type.ShortUniqueIdentifier;

import java.io.IOException;

public class ShortUniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ShortUniqueIdentifier, ShortUniqueIdentifier.ShortUniqueIdentifierBuilder> {

    public ShortUniqueIdentifierJsonDeserializer() {
        super(ShortUniqueIdentifier.kmipTag, ShortUniqueIdentifier.encodingType);
    }

    @Override
    protected ShortUniqueIdentifier.ShortUniqueIdentifierBuilder createBuilder() {
        return ShortUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(ShortUniqueIdentifier.ShortUniqueIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected ShortUniqueIdentifier build(ShortUniqueIdentifier.ShortUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}