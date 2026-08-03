package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ApplicationData;

public class ApplicationDataJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ApplicationData, ApplicationData.ApplicationDataBuilder> {

  public ApplicationDataJsonDeserializer() {
    super(ApplicationData.kmipTag, ApplicationData.encodingType);
  }

  @Override
  protected ApplicationData.ApplicationDataBuilder createBuilder() {
    return ApplicationData.builder();
  }

  @Override
  protected void setValue(ApplicationData.ApplicationDataBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ApplicationData build(ApplicationData.ApplicationDataBuilder builder) {
    return builder.build();
  }
}
