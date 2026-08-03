package org.purpleBean.kmip.codec.ttlv.serializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.v3x0.type.OtpDigest;

public class OtpDigestTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OtpDigest> {

  @Override
  public ByteBuffer serialize(OtpDigest obj, TtlvMapper mapper) throws IOException {
    if (obj == null) {
      return null;
    }
    if (!obj.isSupported()) {
      throw new IOException(
          String.format("%s is not supported for KMIP spec %s", obj
              .getKmipTag()
              .getDescription(), KmipContext.getSpec()));
    }
    int intValue = (Integer) obj.getValue();
    byte[] payload = mapper
        .writeValueAsByteBuffer(intValue)
        .array();
    return TtlvObject
        .builder()
        .tag(obj
            .getKmipTag()
            .getTagBytes())
        .type(obj
            .getEncodingType()
            .getTypeValue())
        .value(payload)
        .build()
        .toByteBuffer();
  }
}
