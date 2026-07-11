package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LoginOpRequestPayload;

import java.io.IOException;

public class LoginOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LoginOpRequestPayload, LoginOpRequestPayload.LoginOpRequestPayloadBuilder> {

    public LoginOpRequestPayloadXmlDeserializer() {
        super(LoginOpRequestPayload.kmipTag, LoginOpRequestPayload.encodingType);
    }

    @Override
    protected LoginOpRequestPayload.LoginOpRequestPayloadBuilder createBuilder() {
        return LoginOpRequestPayload.builder();
    }

    @Override
    protected void setValue(LoginOpRequestPayload.LoginOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.CREDENTIAL -> builder.credential(ctxt.readValue(p, Credential.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected LoginOpRequestPayload build(LoginOpRequestPayload.LoginOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}