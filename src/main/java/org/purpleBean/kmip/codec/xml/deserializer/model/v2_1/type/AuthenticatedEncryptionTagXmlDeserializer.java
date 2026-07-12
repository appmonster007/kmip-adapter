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
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionTag;

import java.io.IOException;

public class AuthenticatedEncryptionTagXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AuthenticatedEncryptionTag, AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder> {

    public AuthenticatedEncryptionTagXmlDeserializer() {
        super(AuthenticatedEncryptionTag.kmipTag, AuthenticatedEncryptionTag.encodingType);
    }

    @Override
    protected AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder createBuilder() {
        return AuthenticatedEncryptionTag.builder();
    }

    @Override
    protected void setValue(AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected AuthenticatedEncryptionTag build(AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder builder) {
        return builder.build();
    }
}