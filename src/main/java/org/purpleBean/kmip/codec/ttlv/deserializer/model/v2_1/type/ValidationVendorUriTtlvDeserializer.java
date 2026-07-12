package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.ValidationVendorUri;

import java.io.IOException;

public class ValidationVendorUriTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationVendorUri, ValidationVendorUri.ValidationVendorUriBuilder> {

    public ValidationVendorUriTtlvDeserializer() {
        super(ValidationVendorUri.kmipTag, ValidationVendorUri.encodingType);
    }

    @Override
    protected ValidationVendorUri.ValidationVendorUriBuilder createBuilder() {
        return ValidationVendorUri.builder();
    }

    @Override
    protected void setValue(ValidationVendorUri.ValidationVendorUriBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected ValidationVendorUri build(ValidationVendorUri.ValidationVendorUriBuilder builder) {
        return builder.build();
    }
}