package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.QuantumSafeCapability;

import java.io.IOException;

public class QuantumSafeCapabilityXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<QuantumSafeCapability, QuantumSafeCapability.QuantumSafeCapabilityBuilder> {

    public QuantumSafeCapabilityXmlDeserializer() {
        super(QuantumSafeCapability.kmipTag, QuantumSafeCapability.encodingType);
    }

    @Override
    protected QuantumSafeCapability.QuantumSafeCapabilityBuilder createBuilder() {
        return QuantumSafeCapability.builder();
    }

    @Override
    protected void setValue(QuantumSafeCapability.QuantumSafeCapabilityBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected QuantumSafeCapability build(QuantumSafeCapability.QuantumSafeCapabilityBuilder builder) {
        return builder.build();
    }
}