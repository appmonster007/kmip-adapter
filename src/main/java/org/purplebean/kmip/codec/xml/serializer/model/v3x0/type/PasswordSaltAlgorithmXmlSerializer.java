package org.purplebean.kmip.codec.xml.serializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.namespace.QName;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purplebean.kmip.model.v3x0.type.PasswordSaltAlgorithm;

/**
 * XML serializer for {@link PasswordSaltAlgorithm}.
 */
public class PasswordSaltAlgorithmXmlSerializer
    extends AbstractKmipDataTypeXmlSerializer<PasswordSaltAlgorithm> {

  @Override
  public void serialize(PasswordSaltAlgorithm obj, JsonGenerator gen,
                        SerializerProvider serializers) throws IOException {
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
    xmlGen.setNextIsAttribute(true);
    xmlGen.writeStringField("type", obj
        .getEncodingType()
        .getDescription());
    xmlGen.setNextIsAttribute(true);
    xmlGen.writeFieldName("value");
    String description = PasswordSaltAlgorithm
        .fromValue((Integer) obj.getValue())
        .getDescription();
    serializers.defaultSerializeValue(description, gen);
    xmlGen.writeEndObject();
  }
}
