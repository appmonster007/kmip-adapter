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
import org.purpleBean.kmip.model.v2_1.type.ProtectionStorageMask;

import java.io.IOException;

public class ProtectionStorageMaskXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtectionStorageMask, ProtectionStorageMask.ProtectionStorageMaskBuilder> {

    public ProtectionStorageMaskXmlDeserializer() {
        super(ProtectionStorageMask.kmipTag, ProtectionStorageMask.encodingType);
    }

    @Override
    protected ProtectionStorageMask.ProtectionStorageMaskBuilder createBuilder() {
        return ProtectionStorageMask.builder();
    }

    @Override
    protected void setValue(ProtectionStorageMask.ProtectionStorageMaskBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected ProtectionStorageMask build(ProtectionStorageMask.ProtectionStorageMaskBuilder builder) {
        return builder.build();
    }
}