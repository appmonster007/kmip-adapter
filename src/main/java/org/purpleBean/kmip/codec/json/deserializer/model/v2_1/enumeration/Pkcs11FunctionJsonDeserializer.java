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
import org.purpleBean.kmip.model.v2_1.enumeration.Pkcs11Function;

import java.io.IOException;

public class Pkcs11FunctionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Pkcs11Function, Pkcs11Function.Pkcs11FunctionBuilder> {

    public Pkcs11FunctionJsonDeserializer() {
        super(Pkcs11Function.kmipTag, Pkcs11Function.encodingType);
    }

    @Override
    protected Pkcs11Function.Pkcs11FunctionBuilder createBuilder() {
        return Pkcs11Function.builder();
    }

    @Override
    protected void setValue(Pkcs11Function.Pkcs11FunctionBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(Pkcs11Function.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected Pkcs11Function build(Pkcs11Function.Pkcs11FunctionBuilder builder) {
        return builder.build();
    }
}