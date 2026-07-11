package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetDefaultsOpResponsePayload;

import java.io.IOException;

public class SetDefaultsOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SetDefaultsOpResponsePayload, SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder> {

    public SetDefaultsOpResponsePayloadXmlDeserializer() {
        super(SetDefaultsOpResponsePayload.kmipTag, SetDefaultsOpResponsePayload.encodingType);
    }

    @Override
    protected SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder createBuilder() {
        return SetDefaultsOpResponsePayload.builder();
    }

    @Override
    protected void setValue(SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected SetDefaultsOpResponsePayload build(SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}