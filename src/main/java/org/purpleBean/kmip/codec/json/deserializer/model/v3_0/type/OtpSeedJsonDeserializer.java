package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.OtpSeed;

import java.io.IOException;

public class OtpSeedJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OtpSeed, OtpSeed.OtpSeedBuilder> {

    public OtpSeedJsonDeserializer() {
        super(OtpSeed.kmipTag, OtpSeed.encodingType);
    }

    @Override
    protected OtpSeed.OtpSeedBuilder createBuilder() {
        return OtpSeed.builder();
    }

    @Override
    protected void setValue(OtpSeed.OtpSeedBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected OtpSeed build(OtpSeed.OtpSeedBuilder builder) {
        return builder.build();
    }
}