package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

public class LinkedObjectIdentifierXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LinkedObjectIdentifier,
        LinkedObjectIdentifier.LinkedObjectIdentifierBuilder> {

  public LinkedObjectIdentifierXmlDeserializer() {
    super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType);
  }

  @Override
  protected LinkedObjectIdentifier.LinkedObjectIdentifierBuilder createBuilder() {
    return LinkedObjectIdentifier.builder();
  }

  @Override
  protected void setValue(LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected LinkedObjectIdentifier build(
      LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder) {
    return builder.build();
  }
}