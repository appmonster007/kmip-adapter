package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.OperationPolicyName;

/**
 * JSON deserializer for {@link OperationPolicyName}.
 */
public class OperationPolicyNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<OperationPolicyName,
        OperationPolicyName.OperationPolicyNameBuilder> {

  /**
   * Constructs a new {@link OperationPolicyNameJsonDeserializer}.
   */
  public OperationPolicyNameJsonDeserializer() {
    super(OperationPolicyName.kmipTag, OperationPolicyName.encodingType);
  }

  @Override
  protected OperationPolicyName.OperationPolicyNameBuilder createBuilder() {
    return OperationPolicyName.builder();
  }

  @Override
  protected void setValue(OperationPolicyName.OperationPolicyNameBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected OperationPolicyName build(OperationPolicyName.OperationPolicyNameBuilder builder) {
    return builder.build();
  }
}
