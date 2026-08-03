package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ContactInformation;

public class ContactInformationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ContactInformation,
        ContactInformation.ContactInformationBuilder> {

  public ContactInformationJsonDeserializer() {
    super(ContactInformation.kmipTag, ContactInformation.encodingType);
  }

  @Override
  protected ContactInformation.ContactInformationBuilder createBuilder() {
    return ContactInformation.builder();
  }

  @Override
  protected void setValue(ContactInformation.ContactInformationBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ContactInformation build(ContactInformation.ContactInformationBuilder builder) {
    return builder.build();
  }
}
