package org.purpleBean.kmip.codec.json.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;

/**
 * Abstract base class for custom JSON deserializers of specific {@link KmipDataType}
 * implementations.
 * <p>
 * This class provides a template for deserializing complex KMIP objects from JSON. It handles
 * the validation of the KMIP tag and encoding type, and orchestrates the parsing of the
 * JSON structure into a builder object, which is then used to construct the final result.
 *
 * @param <T> The type of {@link KmipDataType} being deserialized.
 * @param <B> The type of the builder used to construct the object.
 */
public abstract class AbstractKmipDataTypeJsonDeserializer<T extends KmipDataType, B>
    extends KmipDataTypeJsonDeserializer<T> {

  private final KmipTag kmipTag;
  private final EncodingType encodingType;

  /**
   * Constructs a new deserializer for the specified KMIP tag and encoding type.
   *
   * @param kmipTag      The expected KMIP tag of the object.
   * @param encodingType The expected encoding type of the object.
   */
  protected AbstractKmipDataTypeJsonDeserializer(KmipTag kmipTag, EncodingType encodingType) {
    this.kmipTag = kmipTag;
    this.encodingType = encodingType;
  }

  @Override
  public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    B builder = createBuilder();

    JsonNode node = ctxt.readTree(p);

    if (node == null) {
      ctxt.reportInputMismatch(handledType(), "JSON node cannot be null");
      return null;
    }

    String tag = getTag(node, ctxt, builder);
    if (tag == null) {
      return null;
    }

    String type = getType(node, ctxt, builder);
    if (type == null) {
      return null;
    }

    JsonNode valueNode = node.get("value");
    if (valueNode == null) {
      ctxt.reportInputMismatch(handledType(), "Missing 'value' field");
      return null;
    }

    if (EncodingType.STRUCTURE
        .getDescription()
        .equals(type)) {
      if (!valueNode.isArray()) {
        ctxt.reportInputMismatch(handledType(),
            "Missing or invalid 'value' array for " + handledType().getSimpleName());
        return null;
      }

      for (JsonNode value : valueNode) {
        if (!value.has("tag")) {
          continue;
        }
        if (!value.has("type")) {
          continue;
        }
        JsonParser valueParser = value.traverse(p.getCodec());
        setValue(builder, value
            .get("tag")
            .asText(), type, valueParser, ctxt);
      }
    } else {
      JsonParser valueParser = valueNode.traverse(p.getCodec());
      valueParser.nextToken();
      setValue(builder, tag, type, valueParser, ctxt);
    }

    T result = build(builder);

    verifyVersionSupport(result);
    return result;
  }

  /**
   * Verifies that the deserialized object is supported by the current KMIP specification.
   *
   * @param result The deserialized object.
   * @throws NoSuchElementException if the object is not supported.
   */
  protected void verifyVersionSupport(T result) {
    KmipSpec spec = KmipContext.getSpec();
    if (!result.isSupported()) {
      throw new NoSuchElementException(
          String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
    }
  }

  /**
   * Extracts and validates the KMIP tag from the JSON node.
   *
   * @param node    The JSON node.
   * @param ctxt    The deserialization context.
   * @param builder The builder object.
   * @return The tag string if valid, or null if validation fails.
   * @throws IOException if an I/O error occurs.
   */
  protected String getTag(JsonNode node, DeserializationContext ctxt, B builder)
      throws IOException {
    JsonNode nameNode = node.get("name");
    JsonNode tagNode = node.get("tag");

    String value = null;
    if (nameNode != null && nameNode.isTextual()) {
      value = nameNode.asText();
    } else if (tagNode != null && tagNode.isTextual()) {
      value = tagNode.asText();
    }

    if (value == null) {
      ctxt.reportInputMismatch(KmipTag.class,
          "Expected 'name' or 'tag' field with string value in object");
      return null;
    }

    if (kmipTag != null && !value.equals(kmipTag.getDescription())) {
      ctxt.reportInputMismatch(handledType(), "Expected object with tag " + kmipTag.getValue());
      return null;
    }
    return value;
  }

  /**
   * Extracts and validates the encoding type from the JSON node.
   *
   * @param node    The JSON node.
   * @param ctxt    The deserialization context.
   * @param builder The builder object.
   * @return The type string if valid, or null if validation fails.
   * @throws IOException if an I/O error occurs.
   */
  protected String getType(JsonNode node, DeserializationContext ctxt, B builder)
      throws IOException {
    JsonNode typeNode = node.get("type");
    if (typeNode == null
        || !typeNode.isTextual()
        || (encodingType != null && !encodingType
        .getDescription()
        .equals(typeNode.asText()))
    ) {
      ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
      return null;
    }
    return typeNode.asText();
  }

  /**
   * Creates a new builder instance for constructing the object.
   *
   * @return A new builder instance.
   */
  protected abstract B createBuilder();

  /**
   * Sets a value on the builder based on the parsed JSON data.
   *
   * @param builder The builder instance.
   * @param tag     The tag of the value being set.
   * @param type    The type of the value being set.
   * @param p       The JSON parser positioned at the value.
   * @param ctxt    The deserialization context.
   * @throws IOException if an I/O error occurs.
   */
  protected abstract void setValue(B builder, String tag, String type, JsonParser p,
                                   DeserializationContext ctxt) throws IOException;

  /**
   * Builds the final object from the builder.
   *
   * @param builder The builder instance.
   * @return The constructed object.
   */
  protected abstract T build(B builder);

}
