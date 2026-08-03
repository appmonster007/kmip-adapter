package org.purplebean.kmip.model.core.structure;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;

/**
 * KMIP ProtocolVersion attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class ProtocolVersion implements KmipStructure {

  public static final KmipTag kmipTag = KmipTag.Standard.PROTOCOL_VERSION.inst();
  public static final EncodingType encodingType = EncodingType.STRUCTURE;
  private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.values());

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProtocolVersion.class);
    }
  }

  @NonNull
  private final ProtocolVersionMajor protocolVersionMajor;
  @NonNull
  private final ProtocolVersionMinor protocolVersionMinor;

  @Builder
  private ProtocolVersion(@NonNull ProtocolVersionMajor protocolVersionMajor,
                          @NonNull ProtocolVersionMinor protocolVersionMinor) {
    this.protocolVersionMajor = protocolVersionMajor;
    this.protocolVersionMinor = protocolVersionMinor;
    validate();
  }

  // Static factory methods for validation
  /**
   * Returns the {@link ProtocolVersion} instance wrapping the given value.
   */
  public static ProtocolVersion of(int major, int minor) {
    return ProtocolVersion
        .builder()
        .protocolVersionMajor(ProtocolVersionMajor.of(major))
        .protocolVersionMinor(ProtocolVersionMinor.of(minor))
        .build();
  }

  /**
   * Returns the {@link ProtocolVersion} instance wrapping the given value.
   */
  public static ProtocolVersion of(@NonNull ProtocolVersionMajor protocolVersionMajor,
                                   @NonNull ProtocolVersionMinor protocolVersionMinor) {
    Objects.requireNonNull(protocolVersionMajor, "protocolVersionMajor cannot be null");
    Objects.requireNonNull(protocolVersionMinor, "protocolVersionMinor cannot be null");
    return ProtocolVersion
        .builder()
        .protocolVersionMajor(protocolVersionMajor)
        .protocolVersionMinor(protocolVersionMinor)
        .build();
  }

  private void validate() {
    // No validation needed for this structure
  }

  public KmipDataType[] getValue() {
    return Stream
        .of(protocolVersionMajor, protocolVersionMinor)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  public int getMajor() {
    return protocolVersionMajor.getValue();
  }

  public int getMinor() {
    return protocolVersionMinor.getValue();
  }

  @Override
  public String toString() {
    return String.format("KMIP-ProtocolVersion-V%s.%s", protocolVersionMajor, protocolVersionMinor);
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
    return true;
  }
}
