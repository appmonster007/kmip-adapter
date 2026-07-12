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
import org.purpleBean.kmip.model.v2_1.type.ReplaceExisting;

import java.io.IOException;

public class ReplaceExistingXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ReplaceExisting, ReplaceExisting.ReplaceExistingBuilder> {

    public ReplaceExistingXmlDeserializer() {
        super(ReplaceExisting.kmipTag, ReplaceExisting.encodingType);
    }

    @Override
    protected ReplaceExisting.ReplaceExistingBuilder createBuilder() {
        return ReplaceExisting.builder();
    }

    @Override
    protected void setValue(ReplaceExisting.ReplaceExistingBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected ReplaceExisting build(ReplaceExisting.ReplaceExistingBuilder builder) {
        return builder.build();
    }
}