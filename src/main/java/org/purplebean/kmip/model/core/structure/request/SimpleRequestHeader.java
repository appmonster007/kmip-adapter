package org.purplebean.kmip.model.core.structure.request;

import static org.purplebean.kmip.api.KmipTag.Standard.PROTOCOL_VERSION;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestHeaderStructure;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;

/**
 * KMIP SimpleRequestHeader structure.
 */
@Data
@Builder(toBuilder = true)
public class SimpleRequestHeader implements RequestHeaderStructure {

  private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

  static {
    KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType,
        SimpleRequestHeader.class);
    RequestHeaderStructure.register(KmipSpec.UnknownVersion, SimpleRequestHeader.class,
        SimpleRequestHeader::of);
  }

  @NonNull
  private final ProtocolVersion protocolVersion;

  @Builder
  private SimpleRequestHeader(@NonNull ProtocolVersion protocolVersion) {
    this.protocolVersion = protocolVersion;
    validate();
  }

  /**
   * Returns the {@link SimpleRequestHeader} instance wrapping the given value.
   */
  public static SimpleRequestHeader of(KmipDataType... values) {
    return of(List.of(values));
  }

  /**
   * Returns the {@link SimpleRequestHeader} instance wrapping the given value.
   */
  public static SimpleRequestHeader of(List<KmipDataType> values) {
    var builder = SimpleRequestHeader.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(PROTOCOL_VERSION.inst())) {
      builder.protocolVersion((ProtocolVersion) map
          .get(PROTOCOL_VERSION.inst())
          .get(0));
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // No validation needed for this structure
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
  public KmipDataType[] getValue() {
    return Stream
        .of(protocolVersion)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public boolean isSupported() {
    return supportedVersions.contains(KmipContext.getSpec());
  }
}
