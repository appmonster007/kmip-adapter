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
import org.purpleBean.kmip.model.v2_1.type.ValidationVendorUri;

import java.io.IOException;

public class ValidationVendorUriXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidationVendorUri, ValidationVendorUri.ValidationVendorUriBuilder> {

    public ValidationVendorUriXmlDeserializer() {
        super(ValidationVendorUri.kmipTag, ValidationVendorUri.encodingType);
    }

    @Override
    protected ValidationVendorUri.ValidationVendorUriBuilder createBuilder() {
        return ValidationVendorUri.builder();
    }

    @Override
    protected void setValue(ValidationVendorUri.ValidationVendorUriBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected ValidationVendorUri build(ValidationVendorUri.ValidationVendorUriBuilder builder) {
        return builder.build();
    }
}