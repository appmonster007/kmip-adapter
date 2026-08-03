package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purplebean.kmip.model.core.type.ApplicationData;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;

/**
 * TTLV deserializer for {@link ApplicationSpecificInformation}.
 */
public class ApplicationSpecificInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ApplicationSpecificInformation,
        ApplicationSpecificInformation.ApplicationSpecificInformationBuilder> {

  /**
   * Constructs a new {@link ApplicationSpecificInformationTtlvDeserializer}.
   */
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