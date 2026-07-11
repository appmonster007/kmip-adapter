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
import org.purpleBean.kmip.model.core.type.Pkcs11InputParameters;

import java.io.IOException;

public class Pkcs11InputParametersJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Pkcs11InputParameters, Pkcs11InputParameters.Pkcs11InputParametersBuilder> {

    public Pkcs11InputParametersJsonDeserializer() {
        super(Pkcs11InputParameters.kmipTag, Pkcs11InputParameters.encodingType);
    }

    @Override
    protected Pkcs11InputParameters.Pkcs11InputParametersBuilder createBuilder() {
        return Pkcs11InputParameters.builder();
    }

    @Override
    protected void setValue(Pkcs11InputParameters.Pkcs11InputParametersBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected Pkcs11InputParameters build(Pkcs11InputParameters.Pkcs11InputParametersBuilder builder) {
        return builder.build();
    }
}