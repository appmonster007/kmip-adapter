package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationSpecificInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ApplicationSpecificInformation,
        ApplicationSpecificInformation.ApplicationSpecificInformationBuilder> {

  public ApplicationSpecificInformationTtlvDeserializer() {
    super(ApplicationSpecificInformation.kmipTag, ApplicationSpecificInformation.encodingType);
  }

  @Override
  protected ApplicationSpecificInformation.ApplicationSpecificInformationBuilder createBuilder() {
    return ApplicationSpecificInformation.builder();
  }

  @Override
  protected void setValue(
      ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.APPLICATION_NAMESPACE ->
          builder.applicationNamespace(mapper.readValue(p, ApplicationNamespace.class));
      case KmipTag.Standard.APPLICATION_DATA ->
          builder.applicationData(mapper.readValue(p, ApplicationData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ApplicationSpecificInformation build(
      ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder) {
    return builder.build();
  }
}