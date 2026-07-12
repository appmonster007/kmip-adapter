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
import org.purpleBean.kmip.model.v2_1.enumeration.UniqueIdentifier;

import java.io.IOException;

public class UniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UniqueIdentifier, UniqueIdentifier.UniqueIdentifierBuilder> {

    public UniqueIdentifierJsonDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
    }

    @Override
    protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
        return UniqueIdentifier.builder();
    }

    @Override
    protected void setValue(UniqueIdentifier.UniqueIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(UniqueIdentifier.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected UniqueIdentifier build(UniqueIdentifier.UniqueIdentifierBuilder builder) {
        return builder.build();
    }
}