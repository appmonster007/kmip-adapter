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
import org.purpleBean.kmip.model.core.type.QuantumSafe;

import java.io.IOException;

public class QuantumSafeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<QuantumSafe, QuantumSafe.QuantumSafeBuilder> {

    public QuantumSafeJsonDeserializer() {
        super(QuantumSafe.kmipTag, QuantumSafe.encodingType);
    }

    @Override
    protected QuantumSafe.QuantumSafeBuilder createBuilder() {
        return QuantumSafe.builder();
    }

    @Override
    protected void setValue(QuantumSafe.QuantumSafeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected QuantumSafe build(QuantumSafe.QuantumSafeBuilder builder) {
        return builder.build();
    }
}