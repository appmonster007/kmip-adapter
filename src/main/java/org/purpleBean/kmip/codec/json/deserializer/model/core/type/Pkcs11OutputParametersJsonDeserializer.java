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
import org.purpleBean.kmip.model.core.type.Pkcs11OutputParameters;

import java.io.IOException;

public class Pkcs11OutputParametersJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Pkcs11OutputParameters, Pkcs11OutputParameters.Pkcs11OutputParametersBuilder> {

    public Pkcs11OutputParametersJsonDeserializer() {
        super(Pkcs11OutputParameters.kmipTag, Pkcs11OutputParameters.encodingType);
    }

    @Override
    protected Pkcs11OutputParameters.Pkcs11OutputParametersBuilder createBuilder() {
        return Pkcs11OutputParameters.builder();
    }

    @Override
    protected void setValue(Pkcs11OutputParameters.Pkcs11OutputParametersBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected Pkcs11OutputParameters build(Pkcs11OutputParameters.Pkcs11OutputParametersBuilder builder) {
        return builder.build();
    }
}