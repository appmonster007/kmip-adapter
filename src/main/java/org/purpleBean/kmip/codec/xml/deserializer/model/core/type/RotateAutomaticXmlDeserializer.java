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
import org.purpleBean.kmip.model.core.type.RotateAutomatic;

import java.io.IOException;

public class RotateAutomaticXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RotateAutomatic, RotateAutomatic.RotateAutomaticBuilder> {

    public RotateAutomaticXmlDeserializer() {
        super(RotateAutomatic.kmipTag, RotateAutomatic.encodingType);
    }

    @Override
    protected RotateAutomatic.RotateAutomaticBuilder createBuilder() {
        return RotateAutomatic.builder();
    }

    @Override
    protected void setValue(RotateAutomatic.RotateAutomaticBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected RotateAutomatic build(RotateAutomatic.RotateAutomaticBuilder builder) {
        return builder.build();
    }
}