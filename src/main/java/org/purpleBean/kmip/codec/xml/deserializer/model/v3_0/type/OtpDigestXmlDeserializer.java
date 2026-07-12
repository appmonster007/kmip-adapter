package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.OtpDigest;

import java.io.IOException;

public class OtpDigestXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<OtpDigest, OtpDigest.OtpDigestBuilder> {

    public OtpDigestXmlDeserializer() {
        super(OtpDigest.kmipTag, OtpDigest.encodingType);
    }

    @Override
    protected OtpDigest.OtpDigestBuilder createBuilder() {
        return OtpDigest.builder();
    }

    @Override
    protected void setValue(OtpDigest.OtpDigestBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(OtpDigest.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected OtpDigest build(OtpDigest.OtpDigestBuilder builder) {
        return builder.build();
    }
}