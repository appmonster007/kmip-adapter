package org.purplebean.kmip.model.core.structure;

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
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.DigestValue;
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP Digest attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class Digest implements KmipStructure, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.DIGEST.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Digest.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, Digest.class, Digest::of);
    }
  }

  @NonNull
  private final HashingAlgorithm hashingAlgorithm;
  private final DigestValue digestValue;
  private final KeyFormatType keyFormatType;

  @Builder
  private Digest(
      @NonNull HashingAlgorithm hashingAlgorithm,
      DigestValue digestValue,
      KeyFormatType keyFormatType
  ) {
    this.hashingAlgorithm = hashingAlgorithm;
    this.digestValue = digestValue;
    this.keyFormatType = keyFormatType;
    validate();
  }

  /**
   * Returns the {@link Digest} instance wrapping the given value.
   */
  public static Digest of(@NonNull AttributeName attributeName,
                          @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure)
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return Digest
        .builder()
        .hashingAlgorithm((HashingAlgorithm) map
            .get(HashingAlgorithm.kmipTag)
            .get(0))
        .digestValue((DigestValue) map
            .get(DigestValue.kmipTag)
            .get(0))
        .keyFormatType((KeyFormatType) map
            .get(KeyFormatType.kmipTag)
            .get(0))
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // Validate KMIP spec compatibility
    KmipSpec spec = KmipContext.getSpec();
    if (!hashingAlgorithm.isSupported()) {
      throw new IllegalArgumentException(
          String.format("HashingAlgorithm is not supported for KMIP spec %s", spec)
      );
    }
    if (digestValue != null && !digestValue.isSupported()) {
      throw new IllegalArgumentException(
          String.format("DigestValue is not supported for KMIP spec %s", spec)
      );
    }
    if (keyFormatType != null && !keyFormatType.isSupported()) {
      throw new IllegalArgumentException(
          String.format("KeyFormatType is not supported for KMIP spec %s", spec)
      );
    }
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
        .of(hashingAlgorithm, digestValue, keyFormatType)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public boolean isAlwaysPresent() {
    return false;
  }

  @Override
  public boolean isServerInitializable() {
    return true;
  }

  @Override
  public boolean isClientInitializable() {
    return false;
  }

  @Override
  public boolean isServerModifiable(State state) {
    return false;
  }

  @Override
  public boolean isClientModifiable(State state) {
    return false;
  }

  @Override
  public boolean isClientDeletable() {
    return false;
  }

  @Override
  public boolean isMultiInstanceAllowed() {
    return true;
  }

  @Override
  public String getCanonicalName() {
    return kmipTag.getDescription();
  }

  @Override
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofStructure(getValue());
  }

  @Override
  public AttributeName getAttributeName() {
    return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
  }
}
