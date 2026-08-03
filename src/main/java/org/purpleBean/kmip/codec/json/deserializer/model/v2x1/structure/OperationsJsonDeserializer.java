package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v2x1.structure.Operations;

public class OperationsJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Operations, Operations.OperationsBuilder> {

  public OperationsJsonDeserializer() {
    super(Operations.kmipTag, Operations.encodingType);
  }

  @Override
  protected Operations.OperationsBuilder createBuilder() {
    return Operations.builder();
  }

  @Override
  protected void setValue(Operations.OperationsBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPERATION -> builder.operation(ctxt.readValue(p, Operation.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Operations build(Operations.OperationsBuilder builder) {
    return builder.build();
  }
}