package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

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
import org.purpleBean.kmip.model.core.type.PasswordSalt;

import java.io.IOException;

public class PasswordSaltXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PasswordSalt, PasswordSalt.PasswordSaltBuilder> {

    public PasswordSaltXmlDeserializer() {
        super(PasswordSalt.kmipTag, PasswordSalt.encodingType);
    }

    @Override
    protected PasswordSalt.PasswordSaltBuilder createBuilder() {
        return PasswordSalt.builder();
    }

    @Override
    protected void setValue(PasswordSalt.PasswordSaltBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected PasswordSalt build(PasswordSalt.PasswordSaltBuilder builder) {
        return builder.build();
    }
}