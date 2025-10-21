package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.OperationPolicyName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationPolicyNameTtlvSerializer extends KmipDataTypeTtlvSerializer<OperationPolicyName> {
    @Override
    public ByteBuffer serialize(OperationPolicyName value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(OperationPolicyName value, TtlvMapper mapper) throws IOException {
        if (value == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IOException(
                String.format("%s is not supported for KMIP spec %s",
                        value.getKmipTag().getDescription(), spec)
            );
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
