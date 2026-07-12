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
import org.purpleBean.kmip.model.core.type.Extractable;

import java.io.IOException;

public class ExtractableXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Extractable, Extractable.ExtractableBuilder> {

    public ExtractableXmlDeserializer() {
        super(Extractable.kmipTag, Extractable.encodingType);
    }

    @Override
    protected Extractable.ExtractableBuilder createBuilder() {
        return Extractable.builder();
    }

    @Override
    protected void setValue(Extractable.ExtractableBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected Extractable build(Extractable.ExtractableBuilder builder) {
        return builder.build();
    }
}