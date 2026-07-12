package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.NistSecurityCategory;

import java.io.IOException;

public class NistSecurityCategoryJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NistSecurityCategory, NistSecurityCategory.NistSecurityCategoryBuilder> {

    public NistSecurityCategoryJsonDeserializer() {
        super(NistSecurityCategory.kmipTag, NistSecurityCategory.encodingType);
    }

    @Override
    protected NistSecurityCategory.NistSecurityCategoryBuilder createBuilder() {
        return NistSecurityCategory.builder();
    }

    @Override
    protected void setValue(NistSecurityCategory.NistSecurityCategoryBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected NistSecurityCategory build(NistSecurityCategory.NistSecurityCategoryBuilder builder) {
        return builder.build();
    }
}