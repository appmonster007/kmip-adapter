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
import org.purpleBean.kmip.model.core.structure.PublicKeyAttributes;

import java.io.IOException;

public class PublicKeyAttributesXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PublicKeyAttributes, PublicKeyAttributes.PublicKeyAttributesBuilder> {

    public PublicKeyAttributesXmlDeserializer() {
        super(PublicKeyAttributes.kmipTag, PublicKeyAttributes.encodingType);
    }

    @Override
    protected PublicKeyAttributes.PublicKeyAttributesBuilder createBuilder() {
        return PublicKeyAttributes.builder();
    }

    @Override
    protected void setValue(PublicKeyAttributes.PublicKeyAttributesBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.attribute(ctxt.readValue(p, KmipAttribute.class));
    }

    @Override
    protected PublicKeyAttributes build(PublicKeyAttributes.PublicKeyAttributesBuilder builder) {
        return builder.build();
    }
}