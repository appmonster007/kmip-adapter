package org.purpleBean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.PutOpResponsePayload;

public class PutOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PutOpResponsePayload,
        PutOpResponsePayload.PutOpResponsePayloadBuilder> {

  public PutOpResponsePayloadXmlDeserializer() {
    super(PutOpResponsePayload.kmipTag, PutOpResponsePayload.encodingType);
  }

  @Override
  protected PutOpResponsePayload.PutOpResponsePayloadBuilder createBuilder() {
    return PutOpResponsePayload.builder();
  }

  @Override
  protected void setValue(PutOpResponsePayload.PutOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);

    // No fields to deserialize
  }

  @Override
  protected PutOpResponsePayload build(PutOpResponsePayload.PutOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
