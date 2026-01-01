package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ContactInformation;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ContactInformationTtlvSerializer extends KmipDataTypeTtlvSerializer<ContactInformation> {

    @Override
    public ByteBuffer serialize(ContactInformation contactInformation, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(contactInformation, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(ContactInformation contactInformation, TtlvMapper mapper) throws IOException {
        if (contactInformation == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!contactInformation.isSupported()) {
            throw new IOException(
                    String.format("%s is not supported for KMIP spec %s",
                            contactInformation.getKmipTag().getDescription(), spec)
            );
        }

        byte[] tag = contactInformation.getKmipTag().getTagBytes();
        byte type = contactInformation.getEncodingType().getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(contactInformation.getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}