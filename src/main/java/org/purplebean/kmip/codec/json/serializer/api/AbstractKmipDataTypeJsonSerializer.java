package org.purplebean.kmip.codec.json.serializer.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;

/**
 * Abstract base class for JSON serialization of {@link KmipDataType} objects.
 *
 * <p>This class implements the core logic for serializing KMIP objects to JSON, ensuring
 * compliance with the KMIP JSON encoding specification. It handles the serialization
 * of the KMIP tag, type, and value, including support for nested structures and
 * enumerations.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Version Checking:</b> Verifies that the object is supported by the current
 *       {@link KmipSpec} before serialization.</li>
 *   <li><b>Standard Format:</b> Serializes objects as JSON objects with "tag", "type",
 *       and "value" fields.</li>
 *   <li><b>Structure Support:</b> Recursively serializes nested {@link KmipStructure}
 *       objects.</li>
 *   <li><b>Enumeration Support:</b> Serializes {@link KmipEnumeration} values using
 *       their string descriptions.</li>
 * </ul>
 *
 * @param <T> The type of {@link KmipDataType} to serialize.
 */
public abstract class AbstractKmipDataTypeJsonSerializer<T extends KmipDataType>
    extends KmipDataTypeJsonSerializer<T> {

  @Override
  public void serialize(T obj, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    // Validation: Null check
    if (obj == null) {
      return;
    }

    // Validation: KMIP spec compatibility
    KmipSpec spec = KmipContext.getSpec();
    if (!obj.isSupported()) {
      throw new UnsupportedEncodingException(
          String.format("%s is not supported for KMIP spec %s", obj
              .getKmipTag()
              .getDescription(), spec)
      );
    }

    gen.writeStartObject();

    KmipTag kmipTag = obj.getKmipTag();
    if (kmipTag.isCustom()) {
      gen.writeStringField("tag", kmipTag.getTagHexString());
      gen.writeStringField("name", kmipTag.getDescription());
    } else {
      gen.writeStringField("tag", kmipTag.getDescription());
    }
    gen.writeStringField("type", obj
        .getEncodingType()
        .getDescription());

    gen.writeFieldName("value");
    var value = obj.getValue();
    if (obj.getEncodingType() == EncodingType.STRUCTURE) {
      KmipDataType[] nestedValues = (KmipDataType[]) value;
      if (nestedValues != null) {
        gen.writeStartArray();
        for (KmipDataType kmipDataType : nestedValues) {
          if (kmipDataType != null) {
            serializers.defaultSerializeValue(kmipDataType, gen);
          }
        }
        gen.writeEndArray();
      }
    } else if (obj.getEncodingType() == EncodingType.ENUMERATION) {
      if (value instanceof KmipEnumeration.Value<?> enumValue) {
        serializers.defaultSerializeValue(enumValue.getDescription(), gen);
      } else {
        // KMIP polymorphic types (e.g. UniqueIdentifier) may report ENUMERATION encoding
        // while storing the value as a plain String (enum name). Serialize as-is.
        serializers.defaultSerializeValue(value, gen);
      }
    } else {
      serializers.defaultSerializeValue(value, gen);
    }

    gen.writeEndObject();
  }
}
