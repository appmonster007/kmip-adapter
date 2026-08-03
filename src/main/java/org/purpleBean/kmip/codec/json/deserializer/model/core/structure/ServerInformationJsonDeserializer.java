package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ServerInformation;

public class ServerInformationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ServerInformation,
        ServerInformation.ServerInformationBuilder> {

  public ServerInformationJsonDeserializer() {
    super(ServerInformation.kmipTag, ServerInformation.encodingType);
  }

  @Override
  protected ServerInformation.ServerInformationBuilder createBuilder() {
    return ServerInformation.builder();
  }

  @Override
  protected void setValue(ServerInformation.ServerInformationBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    builder.value(ctxt.readValue(p, KmipDataType.class));
  }

  @Override
  protected ServerInformation build(ServerInformation.ServerInformationBuilder builder) {
    return builder.build();
  }
}