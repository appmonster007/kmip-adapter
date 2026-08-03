package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ApplicationNamespace,
        ApplicationNamespace.ApplicationNamespaceBuilder> {

  public ApplicationNamespaceJsonDeserializer() {
    super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType);
  }

  @Override
  protected ApplicationNamespace.ApplicationNamespaceBuilder createBuilder() {
    return ApplicationNamespace.builder();
  }

  @Override
  protected void setValue(ApplicationNamespace.ApplicationNamespaceBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ApplicationNamespace build(ApplicationNamespace.ApplicationNamespaceBuilder builder) {
    return builder.build();
  }
}
