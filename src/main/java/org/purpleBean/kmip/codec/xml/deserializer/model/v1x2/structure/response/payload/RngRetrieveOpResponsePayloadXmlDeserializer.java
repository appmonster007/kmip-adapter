package org.purpleBean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.RngRetrieveOpResponsePayload;

public class RngRetrieveOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RngRetrieveOpResponsePayload,
        RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder> {

  public RngRetrieveOpResponsePayloadXmlDeserializer() {
    super(RngRetrieveOpResponsePayload.kmipTag, RngRetrieveOpResponsePayload.encodingType);
  }

  @Override
  protected RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder createBuilder() {
    return RngRetrieveOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);

    if (nodeTag.equals(KmipTag.Standard.DATA)) {
      builder.data(ctxt.readValue(p, DataByteString.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngRetrieveOpResponsePayload build(
      RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
