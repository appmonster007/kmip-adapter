package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityUri;

import java.io.IOException;

public class ValidationAuthorityUriJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ValidationAuthorityUri, ValidationAuthorityUri.ValidationAuthorityUriBuilder> {

    public ValidationAuthorityUriJsonDeserializer() {
        super(ValidationAuthorityUri.kmipTag, ValidationAuthorityUri.encodingType);
    }

    @Override
    protected ValidationAuthorityUri.ValidationAuthorityUriBuilder createBuilder() {
        return ValidationAuthorityUri.builder();
    }

    @Override
    protected void setValue(ValidationAuthorityUri.ValidationAuthorityUriBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected ValidationAuthorityUri build(ValidationAuthorityUri.ValidationAuthorityUriBuilder builder) {
        return builder.build();
    }
}