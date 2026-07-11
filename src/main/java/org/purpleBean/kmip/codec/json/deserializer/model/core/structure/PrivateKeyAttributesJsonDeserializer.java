package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

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
import org.purpleBean.kmip.model.core.structure.PrivateKeyAttributes;

import java.io.IOException;

public class PrivateKeyAttributesJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrivateKeyAttributes, PrivateKeyAttributes.PrivateKeyAttributesBuilder> {

    public PrivateKeyAttributesJsonDeserializer() {
        super(PrivateKeyAttributes.kmipTag, PrivateKeyAttributes.encodingType);
    }

    @Override
    protected PrivateKeyAttributes.PrivateKeyAttributesBuilder createBuilder() {
        return PrivateKeyAttributes.builder();
    }

    @Override
    protected void setValue(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.attribute(ctxt.readValue(p, KmipAttribute.class));
    }

    @Override
    protected PrivateKeyAttributes build(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder) {
        return builder.build();
    }
}