package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.ServerInformation;

public class ServerInformationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ServerInformation,
        ServerInformation.ServerInformationBuilder> {

  public ServerInformationXmlDeserializer() {
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