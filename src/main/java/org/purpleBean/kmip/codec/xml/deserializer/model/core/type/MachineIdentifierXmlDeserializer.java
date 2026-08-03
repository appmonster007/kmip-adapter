package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.MachineIdentifier;

public class MachineIdentifierXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<MachineIdentifier,
        MachineIdentifier.MachineIdentifierBuilder> {

  public MachineIdentifierXmlDeserializer() {
    super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType);
  }

  @Override
  protected MachineIdentifier.MachineIdentifierBuilder createBuilder() {
    return MachineIdentifier.builder();
  }

  @Override
  protected void setValue(MachineIdentifier.MachineIdentifierBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected MachineIdentifier build(MachineIdentifier.MachineIdentifierBuilder builder) {
    return builder.build();
  }
}