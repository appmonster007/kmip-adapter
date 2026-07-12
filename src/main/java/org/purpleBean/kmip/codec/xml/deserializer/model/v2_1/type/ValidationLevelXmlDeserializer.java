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
import org.purpleBean.kmip.model.v2_1.type.ValidationLevel;

import java.io.IOException;

public class ValidationLevelXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidationLevel, ValidationLevel.ValidationLevelBuilder> {

    public ValidationLevelXmlDeserializer() {
        super(ValidationLevel.kmipTag, ValidationLevel.encodingType);
    }

    @Override
    protected ValidationLevel.ValidationLevelBuilder createBuilder() {
        return ValidationLevel.builder();
    }

    @Override
    protected void setValue(ValidationLevel.ValidationLevelBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected ValidationLevel build(ValidationLevel.ValidationLevelBuilder builder) {
        return builder.build();
    }
}