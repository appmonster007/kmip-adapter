package org.purplebean.kmip.model.core.structure.request;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;

/**
 * KMIP SimpleRequestPayload structure.
 */
@Data
@Builder(toBuilder = true)
public class SimpleRequestPayload implements RequestPayloadStructure {

  private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

  static {
    KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType,
        SimpleRequestPayload.class);
    RequestPayloadStructure.register(KmipSpec.UnknownVersion, null, SimpleRequestPayload.class,
        SimpleRequestPayload::of);
  }

  @Builder
  private SimpleRequestPayload() {
    validate();
  }

  /**
   * Returns the {@link SimpleRequestPayload} instance wrapping the given value.
   */
  public static SimpleRequestPayload of() {
    return SimpleRequestPayload
        .builder()
        .build();
  }

  /**
   * Returns the {@link SimpleRequestPayload} instance wrapping the given value.
   */
  public static SimpleRequestPayload of(List<KmipDataType> values) {
    return SimpleRequestPayload
        .builder()
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // Add validation logic here
  }

  @Override
  public KmipTag getKmipTag() {
    return kmipTag;
  }

  @Override
  public EncodingType getEncodingType() {
    return encodingType;
  }

  @Override
  public boolean isSupported() {
    return supportedVersions.contains(KmipContext.getSpec());
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of()
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return null;
  }
}
