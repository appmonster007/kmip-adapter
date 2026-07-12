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
import org.purpleBean.kmip.model.v2_1.type.SubmissionDate;

import java.io.IOException;

public class SubmissionDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SubmissionDate, SubmissionDate.SubmissionDateBuilder> {

    public SubmissionDateTtlvDeserializer() {
        super(SubmissionDate.kmipTag, SubmissionDate.encodingType);
    }

    @Override
    protected SubmissionDate.SubmissionDateBuilder createBuilder() {
        return SubmissionDate.builder();
    }

    @Override
    protected void setValue(SubmissionDate.SubmissionDateBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected SubmissionDate build(SubmissionDate.SubmissionDateBuilder builder) {
        return builder.build();
    }
}