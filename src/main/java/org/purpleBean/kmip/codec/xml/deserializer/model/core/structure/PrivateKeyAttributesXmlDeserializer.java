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
import org.purpleBean.kmip.model.core.structure.PrivateKeyAttributes;

import java.io.IOException;

public class PrivateKeyAttributesXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PrivateKeyAttributes, PrivateKeyAttributes.PrivateKeyAttributesBuilder> {

    public PrivateKeyAttributesXmlDeserializer() {
        super(PrivateKeyAttributes.kmipTag, PrivateKeyAttributes.encodingType);
    }

    @Override
    protected PrivateKeyAttributes.PrivateKeyAttributesBuilder createBuilder() {
        return PrivateKeyAttributes.builder();
    }

    @Override
    protected void setValue(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.attribute(ctxt.readValue(p, KmipAttribute.class));
    }

    @Override
    protected PrivateKeyAttributes build(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder) {
        return builder.build();
    }
}