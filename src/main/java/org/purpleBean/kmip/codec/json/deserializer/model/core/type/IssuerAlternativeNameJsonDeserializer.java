package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;

public class IssuerAlternativeNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<IssuerAlternativeName,
        IssuerAlternativeName.IssuerAlternativeNameBuilder> {

  public IssuerAlternativeNameJsonDeserializer() {
    super(IssuerAlternativeName.kmipTag, IssuerAlternativeName.encodingType);
  }

  @Override
  protected IssuerAlternativeName.IssuerAlternativeNameBuilder createBuilder() {
    return IssuerAlternativeName.builder();
  }

  @Override
  protected void setValue(IssuerAlternativeName.IssuerAlternativeNameBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected IssuerAlternativeName build(
      IssuerAlternativeName.IssuerAlternativeNameBuilder builder) {
    return builder.build();
  }
}
