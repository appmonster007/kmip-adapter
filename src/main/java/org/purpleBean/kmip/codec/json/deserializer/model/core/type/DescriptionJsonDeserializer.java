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
import org.purpleBean.kmip.model.core.type.Description;

import java.io.IOException;

public class DescriptionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Description, Description.DescriptionBuilder> {

    public DescriptionJsonDeserializer() {
        super(Description.kmipTag, Description.encodingType);
    }

    @Override
    protected Description.DescriptionBuilder createBuilder() {
        return Description.builder();
    }

    @Override
    protected void setValue(Description.DescriptionBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected Description build(Description.DescriptionBuilder builder) {
        return builder.build();
    }
}