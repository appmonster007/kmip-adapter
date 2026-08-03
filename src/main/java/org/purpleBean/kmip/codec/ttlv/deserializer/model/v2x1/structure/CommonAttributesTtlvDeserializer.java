package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.structure.CommonAttributes;

public class CommonAttributesTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CommonAttributes,
        CommonAttributes.CommonAttributesBuilder> {

  public CommonAttributesTtlvDeserializer() {
    super(CommonAttributes.kmipTag, CommonAttributes.encodingType);
  }

  @Override
  protected CommonAttributes.CommonAttributesBuilder createBuilder() {
    return CommonAttributes.builder();
  }

  @Override
  protected void setValue(CommonAttributes.CommonAttributesBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.attribute(mapper.readValue(p, KmipAttribute.class));
  }

  @Override
  protected CommonAttributes build(CommonAttributes.CommonAttributesBuilder builder) {
    return builder.build();
  }
}