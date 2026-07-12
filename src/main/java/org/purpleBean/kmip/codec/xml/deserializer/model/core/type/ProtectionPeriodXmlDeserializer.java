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
import org.purpleBean.kmip.model.core.type.ProtectionPeriod;

import java.io.IOException;

public class ProtectionPeriodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtectionPeriod, ProtectionPeriod.ProtectionPeriodBuilder> {

    public ProtectionPeriodXmlDeserializer() {
        super(ProtectionPeriod.kmipTag, ProtectionPeriod.encodingType);
    }

    @Override
    protected ProtectionPeriod.ProtectionPeriodBuilder createBuilder() {
        return ProtectionPeriod.builder();
    }

    @Override
    protected void setValue(ProtectionPeriod.ProtectionPeriodBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Long.class));
    }

    @Override
    protected ProtectionPeriod build(ProtectionPeriod.ProtectionPeriodBuilder builder) {
        return builder.build();
    }
}