package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.OtpDigest;

import java.io.IOException;

public class OtpDigestTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OtpDigest, OtpDigest.OtpDigestBuilder> {

    public OtpDigestTtlvDeserializer() {
        super(OtpDigest.kmipTag, OtpDigest.encodingType);
    }

    @Override
    protected OtpDigest.OtpDigestBuilder createBuilder() {
        return OtpDigest.builder();
    }

    @Override
    protected void setValue(OtpDigest.OtpDigestBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(OtpDigest.fromValue(mapper.readValue(p, Integer.class)));
    }

    @Override
    protected OtpDigest build(OtpDigest.OtpDigestBuilder builder) {
        return builder.build();
    }
}