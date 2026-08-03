package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.CurrentAttribute;

public class CurrentAttributeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CurrentAttribute,
        CurrentAttribute.CurrentAttributeBuilder> {

  public CurrentAttributeJsonDeserializer() {
    super(CurrentAttribute.kmipTag, CurrentAttribute.encodingType);
  }

  @Override
  protected CurrentAttribute.CurrentAttributeBuilder createBuilder() {
    return CurrentAttribute.builder();
  }

  @Override
  protected void setValue(CurrentAttribute.CurrentAttributeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    JsonNode childNode = ctxt.readTree(p);
    EncodingType childEncodingType = EncodingType
        .fromName(childNode
            .get("type")
            .asText())
        .orElseThrow(() -> new IllegalArgumentException(
            "Unknown encoding type in CurrentAttribute child: " + childNode
                .get("type")
                .asText()));
    Class<? extends KmipDataType> clazz = getKmipDataTypeClass(nodeTag, childEncodingType, ctxt);
    JsonParser childParser = childNode.traverse(p.getCodec());
    childParser.nextToken();
    builder.attribute((KmipAttribute) ctxt.readValue(childParser, clazz));
  }

  @Override
  protected CurrentAttribute build(CurrentAttribute.CurrentAttributeBuilder builder) {
    return builder.build();
  }
}
