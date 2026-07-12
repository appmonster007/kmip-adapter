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
import org.purpleBean.kmip.model.v2_1.type.Pkcs12FriendlyName;

import java.io.IOException;

public class Pkcs12FriendlyNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Pkcs12FriendlyName, Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder> {

    public Pkcs12FriendlyNameXmlDeserializer() {
        super(Pkcs12FriendlyName.kmipTag, Pkcs12FriendlyName.encodingType);
    }

    @Override
    protected Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder createBuilder() {
        return Pkcs12FriendlyName.builder();
    }

    @Override
    protected void setValue(Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected Pkcs12FriendlyName build(Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder builder) {
        return builder.build();
    }
}