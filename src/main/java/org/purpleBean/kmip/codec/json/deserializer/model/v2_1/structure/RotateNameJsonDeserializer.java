package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.enumeration.RotateNameType;
import org.purpleBean.kmip.model.v2_1.structure.RotateName;
import org.purpleBean.kmip.model.v2_1.type.RotateNameValue;

import java.io.IOException;

public class RotateNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RotateName, RotateName.RotateNameBuilder> {

    public RotateNameJsonDeserializer() {
        super(RotateName.kmipTag, RotateName.encodingType);
    }

    @Override
    protected RotateName.RotateNameBuilder createBuilder() {
        return RotateName.builder();
    }

    @Override
    protected void setValue(RotateName.RotateNameBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ROTATE_NAME_VALUE -> builder.rotateNameValue(ctxt.readValue(p, RotateNameValue.class));
            case KmipTag.Standard.ROTATE_NAME_TYPE -> builder.rotateNameType(ctxt.readValue(p, RotateNameType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RotateName build(RotateName.RotateNameBuilder builder) {
        return builder.build();
    }
}