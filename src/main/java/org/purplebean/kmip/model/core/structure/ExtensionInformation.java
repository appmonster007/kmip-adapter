package org.purplebean.kmip.model.core.structure;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.type.ExtensionName;
import org.purplebean.kmip.model.core.type.ExtensionTag;
import org.purplebean.kmip.model.core.type.ExtensionType;

/**
 * KMIP ExtensionInformation attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class ExtensionInformation implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.EXTENSION_INFORMATION.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ExtensionInformation.class);
    }
  }

  @NonNull
  private final ExtensionName extensionName;

  private final ExtensionTag extensionTag;

  private final ExtensionType extensionType;

  @Builder
  private ExtensionInformation(
      @NonNull ExtensionName extensionName,
      ExtensionTag extensionTag,
      ExtensionType extensionType
  ) {
    this.extensionName = extensionName;
    this.extensionTag = extensionTag;
    this.extensionType = extensionType;
    validate();
  }

  /**
   * Returns the {@link ExtensionInformation} instance wrapping the given value.
   */
  public static ExtensionInformation of(
      @NonNull ExtensionName extensionName,
      ExtensionTag extensionTag,
      ExtensionType extensionType
  ) {
    return ExtensionInformation
        .builder()
        .extensionName(extensionName)
        .extensionTag(extensionTag)
        .extensionType(extensionType)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(extensionName, "ExtensionName cannot be null");
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
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(
            extensionName,
            extensionTag,
            extensionType)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
