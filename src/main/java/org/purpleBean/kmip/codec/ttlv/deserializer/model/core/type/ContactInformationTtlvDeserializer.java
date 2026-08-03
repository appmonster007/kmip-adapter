package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ContactInformation;

public class ContactInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ContactInformation,
        ContactInformation.ContactInformationBuilder> {

  public ContactInformationTtlvDeserializer() {
    super(ContactInformation.kmipTag, ContactInformation.encodingType);
  }

  @Override
  protected ContactInformation.ContactInformationBuilder createBuilder() {
    return ContactInformation.builder();
  }

  @Override
  protected void setValue(ContactInformation.ContactInformationBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected ContactInformation build(ContactInformation.ContactInformationBuilder builder) {
    return builder.build();
  }
}
