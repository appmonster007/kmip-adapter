package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

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
import org.purpleBean.kmip.model.core.structure.Constraint;

import java.io.IOException;

public class ConstraintXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Constraint, Constraint.ConstraintBuilder> {

    public ConstraintXmlDeserializer() {
        super(Constraint.kmipTag, Constraint.encodingType);
    }

    @Override
    protected Constraint.ConstraintBuilder createBuilder() {
        return Constraint.builder();
    }

    @Override
    protected void setValue(Constraint.ConstraintBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected Constraint build(Constraint.ConstraintBuilder builder) {
        return builder.build();
    }
}