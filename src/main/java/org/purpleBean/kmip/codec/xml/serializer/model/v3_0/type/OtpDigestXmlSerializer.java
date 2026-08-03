package org.purpleBean.kmip.codec.xml.serializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.namespace.QName;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.v3_0.type.OtpDigest;

public class OtpDigestXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OtpDigest> {

  @Override
  public void serialize(OtpDigest obj, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    if (!obj.isSupported()) {
      throw new UnsupportedEncodingException(
          String.format("%s not supported for KMIP spec %s", obj
              .getClass()
              .getSimpleName(), KmipContext.getSpec()));
    }
    if (!(gen instanceof ToXmlGenerator xmlGen)) {
      throw new IllegalStateException("Expected ToXmlGenerator");
    }
    xmlGen.setNextName(new QName(obj
        .getKmipTag()
        .getDescription()));
    xmlGen.writeStartObject();
    String description = OtpDigest
        .fromValue((Integer) obj.getValue())
        .getDescription();
    xmlGen.setNextIsAttribute(true);
    xmlGen.writeStringField("type", obj
        .getEncodingType()
        .getDescription());
    xmlGen.setNextIsAttribute(true);
    xmlGen.writeFieldName("value");
    serializers.defaultSerializeValue(description, gen);
    xmlGen.writeEndObject();
  }
}
