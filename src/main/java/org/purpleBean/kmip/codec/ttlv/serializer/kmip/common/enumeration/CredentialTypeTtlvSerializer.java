package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialTypeTtlvSerializer extends KmipDataTypeTtlvSerializer<CredentialType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CREDENTIAL_TYPE);

    @Override
    public ByteBuffer serialize(CredentialType value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(CredentialType value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IOException(
                String.format("%s '%s' is not supported for KMIP spec %s",
                    kmipTag.getDescription(), value.getDescription(), spec));
        }

        byte[] tag = kmipTag.getTagBytes();
        byte type = value.getEncodingType().getTypeValue();
        byte[] payload = ByteBuffer.allocate(4).order(TtlvConstants.BYTE_ORDER)
            .putInt(value.getValue().getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
