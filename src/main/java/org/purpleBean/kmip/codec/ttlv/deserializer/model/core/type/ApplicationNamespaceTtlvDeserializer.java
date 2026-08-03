package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ApplicationNamespace,
        ApplicationNamespace.ApplicationNamespaceBuilder> {

  public ApplicationNamespaceTtlvDeserializer() {
    super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType);
  }

  @Override
  protected ApplicationNamespace.ApplicationNamespaceBuilder createBuilder() {
    return ApplicationNamespace.builder();
  }

  @Override
  protected void setValue(ApplicationNamespace.ApplicationNamespaceBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected ApplicationNamespace build(ApplicationNamespace.ApplicationNamespaceBuilder builder) {
    return builder.build();
  }
}