package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.OtpCredential;

import java.io.IOException;

public class OtpCredentialJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OtpCredential, OtpCredential.OtpCredentialBuilder> {

    public OtpCredentialJsonDeserializer() {
        super(OtpCredential.kmipTag, OtpCredential.encodingType);
    }

    @Override
    protected OtpCredential.OtpCredentialBuilder createBuilder() {
        return OtpCredential.builder();
    }

    @Override
    protected void setValue(OtpCredential.OtpCredentialBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        if (nodeTag == OtpAlgorithm.kmipTag.getValue()) {
            builder.otpAlgorithm(ctxt.readValue(p, OtpAlgorithm.class));
        } else if (nodeTag == OtpDigest.kmipTag.getValue()) {
            builder.otpDigest(ctxt.readValue(p, OtpDigest.class));
        } else if (nodeTag == OtpSerial.kmipTag.getValue()) {
            builder.otpSerial(ctxt.readValue(p, OtpSerial.class));
        } else if (nodeTag == OtpSeed.kmipTag.getValue()) {
            builder.otpSeed(ctxt.readValue(p, OtpSeed.class));
        } else if (nodeTag == OtpInterval.kmipTag.getValue()) {
            builder.otpInterval(ctxt.readValue(p, OtpInterval.class));
        } else if (nodeTag == OtpDigits.kmipTag.getValue()) {
            builder.otpDigits(ctxt.readValue(p, OtpDigits.class));
        } else if (nodeTag == OtpCounter.kmipTag.getValue()) {
            builder.otpCounter(ctxt.readValue(p, OtpCounter.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected OtpCredential build(OtpCredential.OtpCredentialBuilder builder) {
        return builder.build();
    }
}