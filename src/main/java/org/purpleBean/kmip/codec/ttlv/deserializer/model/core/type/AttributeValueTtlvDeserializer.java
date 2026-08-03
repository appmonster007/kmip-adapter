package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Stack;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.util.StringUtils;

public class AttributeValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttributeValue, AttributeValue.AttributeValueBuilder> {

  private final Stack<EncodingType> encodingTypeStack = new Stack<>();
  private final Stack<Object> valueStack = new Stack<>();

  public AttributeValueTtlvDeserializer() {
    super(AttributeValue.kmipTag, null);
  }

  @Override
  protected AttributeValue.AttributeValueBuilder createBuilder() {
    return AttributeValue.builder();
  }

  @Override
  protected void setValue(AttributeValue.AttributeValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    EncodingType encodingType = encodingTypeStack.peek();
    switch (encodingType) {
      case INTEGER -> valueStack.push(mapper.readValue(p, Integer.class));
      case LONG_INTEGER -> valueStack.push(mapper.readValue(p, Long.class));
      case BIG_INTEGER -> valueStack.push(mapper.readValue(p, BigInteger.class));
      case BOOLEAN -> valueStack.push(mapper.readValue(p, Boolean.class));
      case TEXT_STRING -> valueStack.push(mapper.readValue(p, String.class));
      case BYTE_STRING -> valueStack.push(mapper.readValue(p, ByteBuffer.class));
      case DATE_TIME -> valueStack.push(mapper.readValue(p, OffsetDateTime.class));
      case INTERVAL -> valueStack.push(mapper.readValue(p, Integer.class));
      case ENUMERATION -> {
        String attributeName = (String) mapper.getAttribute("attributeName");
        KmipTag.Value nodeTag =
            KmipTag.fromName(StringUtils.convertTitleToPascalCase(attributeName));
        var factory = KmipEnumeration.getFromValue(nodeTag);
        Integer value = mapper.readValue(p, Integer.class);
        if (factory == null) {
          throw new IllegalArgumentException(
              String.format("Invalid value [%d] for enumeration tag %s", value,
                  nodeTag.getDescription()));
        }
        valueStack.push(factory.apply(value));
      }
      case STRUCTURE -> {
        if (valueStack.peek() instanceof List<?>) {
          List<KmipDataType> valueList = (List<KmipDataType>) valueStack.peek();
          valueList.add(mapper.readValue(p, KmipDataType.class));
        }
      }
      default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
    }
  }

  @Override
  protected AttributeValue build(AttributeValue.AttributeValueBuilder builder) {
    EncodingType encodingType = encodingTypeStack.pop();
    Object value = valueStack.pop();
    if (encodingType == EncodingType.STRUCTURE && value instanceof List<?> valueList) {
      builder.value(valueList.toArray(KmipDataType[]::new));
    } else {
      builder.value(value);
    }
    return builder.build();
  }

  @Override
  protected byte verifyType(TtlvObject obj, TtlvMapper mapper,
                            AttributeValue.AttributeValueBuilder builder) {
    byte type = obj.getType();
    EncodingType encodingType = EncodingType
        .fromTypeValue(type)
        .orElseThrow(
            () -> new IllegalArgumentException("Unknown encoding type: " + HexFormat
                .of()
                .toHexDigits(type))
        );

    encodingTypeStack.push(encodingType);
    if (encodingType == EncodingType.STRUCTURE) {
      valueStack.push(new ArrayList<KmipDataType>());
    }
    builder.encodingType(encodingType);
    return obj.getType();
  }
}