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
import org.purpleBean.kmip.model.core.type.PasswordSaltAlgorithm;

import java.io.IOException;

public class PasswordSaltAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PasswordSaltAlgorithm, PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder> {

    public PasswordSaltAlgorithmJsonDeserializer() {
        super(PasswordSaltAlgorithm.kmipTag, PasswordSaltAlgorithm.encodingType);
    }

    @Override
    protected PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder createBuilder() {
        return PasswordSaltAlgorithm.builder();
    }

    @Override
    protected void setValue(PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(PasswordSaltAlgorithm.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected PasswordSaltAlgorithm build(PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder builder) {
        return builder.build();
    }
}