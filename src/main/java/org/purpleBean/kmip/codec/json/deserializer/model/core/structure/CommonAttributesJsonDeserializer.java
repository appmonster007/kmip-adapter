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
import org.purpleBean.kmip.model.core.structure.CommonAttributes;

import java.io.IOException;

public class CommonAttributesJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CommonAttributes, CommonAttributes.CommonAttributesBuilder> {

    public CommonAttributesJsonDeserializer() {
        super(CommonAttributes.kmipTag, CommonAttributes.encodingType);
    }

    @Override
    protected CommonAttributes.CommonAttributesBuilder createBuilder() {
        return CommonAttributes.builder();
    }

    @Override
    protected void setValue(CommonAttributes.CommonAttributesBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.attribute(ctxt.readValue(p, KmipAttribute.class));
    }

    @Override
    protected CommonAttributes build(CommonAttributes.CommonAttributesBuilder builder) {
        return builder.build();
    }
}