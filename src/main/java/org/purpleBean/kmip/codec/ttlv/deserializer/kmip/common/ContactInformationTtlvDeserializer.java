package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ContactInformation;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ContactInformationTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ContactInformation> {
    private final KmipTag kmipTag = ContactInformation.kmipTag;
    private final EncodingType encodingType = ContactInformation.encodingType;

    @Override
    public ContactInformation deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        String dt = mapper.readValue(bb, String.class);
        ContactInformation contactInformation = ContactInformation.builder().value(dt).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!contactInformation.isSupported()) {
            throw new NoSuchElementException(String.format("ContactInformation not supported for spec %s", spec));
        }
        return contactInformation;
    }
}
