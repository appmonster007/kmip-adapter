package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.OperationPolicyName;

public class OperationPolicyNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<OperationPolicyName,
        OperationPolicyName.OperationPolicyNameBuilder> {

  public OperationPolicyNameXmlDeserializer() {
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