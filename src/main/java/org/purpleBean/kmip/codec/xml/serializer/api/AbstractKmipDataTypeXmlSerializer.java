package org.purpleBean.kmip.codec.xml.serializer.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.namespace.QName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipMaskType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;

/**
 * Abstract base class for XML serialization of {@link KmipDataType} objects.
 * <p>
 * This class implements the core logic for serializing KMIP objects to XML, ensuring
 * compliance with the KMIP XML encoding specification. It handles the serialization
 * of the KMIP tag (as the element name or 'tag' attribute), type (as the 'type' attribute),
 * and value, including support for nested structures and enumerations.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Version Checking:</b> Verifies that the object is supported by the current
 *       {@link KmipSpec} before serialization.</li>
 *   <li><b>Tag Handling:</b> Uses the KMIP tag description as the XML element name for
 *       standard tags, or uses a generic "TTLV" element with a "tag" attribute for
 *       custom tags (hex strings).</li>
 *   <li><b>Structure Support:</b> Recursively serializes nested {@link KmipStructure}
 *       objects.</li>
 *   <li><b>Enumeration Support:</b> Serializes {@link KmipEnumeration} values using
 *       their string descriptions.</li>
 * </ul>
 *
 * @param <T> The type of {@link KmipDataType} to serialize.
 */
public abstract class AbstractKmipDataTypeXmlSerializer<T extends KmipDataType>
    extends KmipDataTypeXmlSerializer<T> {

  @Override
  public void serialize(T obj, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    // Validation: KMIP spec compatibility
    KmipSpec spec = KmipContext.getSpec();
    if (!obj.isSupported()) {
      throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", obj
          .getClass()
          .getSimpleName(), spec));
    }

    if (!(gen instanceof ToXmlGenerator xmlGen)) {
      throw new IllegalStateException("Expected ToXmlGenerator");
    }

    if (!obj
        .getKmipTag()
        .getDescription()
        .matches("^[0-9][xX].*")) {
      xmlGen.setNextName(new QName(obj
          .getKmipTag()
          .getDescription()));
      xmlGen.writeStartObject();
    } else {
      xmlGen.setNextName(new QName("TTLV"));
      xmlGen.writeStartObject();
      xmlGen.setNextIsAttribute(true);
      xmlGen.writeStringField("tag", obj
          .getKmipTag()
          .getDescription());
    }

    if (obj.getEncodingType() == EncodingType.STRUCTURE) {
      KmipDataType[] values = (KmipDataType[]) obj.getValue();
      if (values != null) {
        for (KmipDataType kmipDataType : values) {
          if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
            serializers.defaultSerializeField(kmipDataType
                .getKmipTag()
                .getDescription(), kmipDataType, gen);
          }
        }
      }
    } else {
      Object value;
      if (obj.getEncodingType() == EncodingType.ENUMERATION) {
        Object rawValue = obj.getValue();
        if (rawValue instanceof KmipEnumeration.Value<?> enumValue) {
          value = enumValue.getDescription();
        } else {
          // KMIP polymorphic types (e.g. UniqueIdentifier) may report ENUMERATION encoding
          // while storing the value as a plain String (enum name). Serialize as-is.
          value = rawValue;
        }
      } else {
        if (obj instanceof KmipMaskType mask) {
          value = mask.getMaskString() != null ? mask.getMaskString() : obj.getValue();
        } else {
          value = obj.getValue();
        }
      }
      xmlGen.setNextIsAttribute(true);
      xmlGen.writeStringField("type", obj
          .getEncodingType()
          .getDescription());

      xmlGen.setNextIsAttribute(true);
      xmlGen.writeFieldName("value");
      serializers.defaultSerializeValue(value, gen);
    }
    xmlGen.writeEndObject();
  }
}
