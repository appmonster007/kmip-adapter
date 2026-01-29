package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ContactInformation;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ContactInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ContactInformation, ContactInformation.ContactInformationBuilder> {

    public ContactInformationTtlvDeserializer() {
        super(ContactInformation.kmipTag, ContactInformation.encodingType);
    }

    @Override
    protected ContactInformation.ContactInformationBuilder createBuilder() {
        return ContactInformation.builder();
    }

    @Override
    protected void setValue(ContactInformation.ContactInformationBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected ContactInformation build(ContactInformation.ContactInformationBuilder builder) {
        return builder.build();
    }
}
