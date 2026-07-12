package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.SaltedPassword;

import java.io.IOException;

public class SaltedPasswordXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SaltedPassword, SaltedPassword.SaltedPasswordBuilder> {

    public SaltedPasswordXmlDeserializer() {
        super(SaltedPassword.kmipTag, SaltedPassword.encodingType);
    }

    @Override
    protected SaltedPassword.SaltedPasswordBuilder createBuilder() {
        return SaltedPassword.builder();
    }

    @Override
    protected void setValue(SaltedPassword.SaltedPasswordBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected SaltedPassword build(SaltedPassword.SaltedPasswordBuilder builder) {
        return builder.build();
    }
}