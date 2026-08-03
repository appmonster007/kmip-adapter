package org.purplebean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;

/**
 * Base XML deserializer for {@link KmipDataType} objects.
 *
 * <p>This class provides the common logic for deserializing KMIP data types from XML.
 * It handles the extraction of the KMIP tag (from the XML element name) and encoding type
 * (from the 'type' attribute) and uses the {@link KmipDataType} registry to find the
 * appropriate concrete class to instantiate.
 *
 * @param <T> The specific type of {@link KmipDataType} to deserialize.
 */
public class KmipDataTypeXmlDeserializer<T extends KmipDataType>
    extends JsonDeserializer<KmipDataType> {

  @Override
  public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

    String currentName;
    if (p instanceof FromXmlParser xmlParser) {
      currentName = xmlParser
          .getStaxReader()
          .getLocalName();
    } else {
      currentName = (String) ctxt.getAttribute("tag");
    }

    TokenBuffer buffer = new TokenBuffer(p, ctxt);
    buffer.copyCurrentStructure(p);

    JsonParser peeker = buffer.asParser();
    EncodingType encodingType = EncodingType.STRUCTURE;

    if (peeker.currentToken() == null) {
      peeker.nextToken();
    }

    if (peeker.currentToken() != JsonToken.START_OBJECT) {
      peeker.nextToken();
    }

    if (peeker.currentToken() == JsonToken.START_OBJECT) {
      if (peeker.nextToken() != JsonToken.END_OBJECT) {
        if (peeker.currentToken() == JsonToken.FIELD_NAME) {
          String fieldName = peeker.currentName();

          peeker.nextToken(); // Move to the value token
          if ("type".equalsIgnoreCase(fieldName)) {
            String type = peeker.getText();
            Optional<EncodingType> optionalEncodingType = EncodingType.fromName(type);
            if (optionalEncodingType.isEmpty()) {
              ctxt.reportInputMismatch(KmipDataType.class,
                  "Missing or invalid 'type' KmipDataType");
              return null;
            }
            encodingType = optionalEncodingType.get();
          }
        }
      }
    }

    KmipTag.Value kmipTag = KmipTag.fromName(currentName);
    Class<? extends KmipDataType> clazz = getKmipDataTypeClass(kmipTag, encodingType, ctxt);
    if (clazz == null) {
      throw new NoSuchElementException(
          String.format("No class registered for tag %s and encoding type %s", kmipTag.getValue(),
              encodingType));
    }

    ctxt.setAttribute("tag", kmipTag.getDescription());
    return (T) ctxt.readValue(buffer.asParser(), clazz);
  }

  /**
   * Determines the concrete {@link KmipDataType} class to instantiate based on the tag and
   * encoding type.
   *
   * @param kmipTag      The KMIP tag.
   * @param encodingType The encoding type.
   * @param ctxt         The deserialization context.
   * @return The concrete class to instantiate.
   */
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
  }

  @Override
  public Class<?> handledType() {
    // Try to infer the generic parameter (T) from the concrete subclass declaration
    // Handles both raw classes and parameterized types (template classes)
    Type superType = getClass().getGenericSuperclass();
    if (superType instanceof ParameterizedType pt) {
      Type typeArg = pt.getActualTypeArguments()[0];
      if (typeArg instanceof Class<?> c) {
        return c;
      }
      if (typeArg instanceof ParameterizedType parameterized) {
        Type raw = parameterized.getRawType();
        if (raw instanceof Class<?> rc) {
          return rc;
        }
      }
    }
    return super.handledType();
  }
}
