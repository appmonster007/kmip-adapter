package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.ContactInformation;

/**
 * XML deserializer for {@link ContactInformation}.
 */
public class ContactInformationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ContactInformation,
        ContactInformation.ContactInformationBuilder> {

  /**
   * Constructs a new {@link ContactInformationXmlDeserializer}.
   */
  public ContactInformationXmlDeserializer() {
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