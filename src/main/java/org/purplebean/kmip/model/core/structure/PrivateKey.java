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
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;

/**
 * KMIP PrivateKey attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class PrivateKey implements ManagedObject, KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.PRIVATE_KEY.inst();
  public static final ObjectType.Value objectTypeValue = ObjectType.Standard.PRIVATE_KEY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, PrivateKey.class);
      ManagedObject.register(spec, kmipTag.getValue(), encodingType, objectTypeValue,
          PrivateKey.class, PrivateKey::of);
    }
  }

  @NonNull
  private final KeyBlock keyBlock;

  @Builder
  private PrivateKey(
      @NonNull KeyBlock keyBlock
  ) {
    this.keyBlock = keyBlock;
    validate();
  }

  /**
   * Returns the {@link PrivateKey} instance wrapping the given value.
   */
  public static PrivateKey of(
      @NonNull KeyBlock keyBlock
  ) {
    return PrivateKey
        .builder()
        .keyBlock(keyBlock)
        .build();
  }

  /**
   * Returns the {@link PrivateKey} instance wrapping the given value.
   */
  public static PrivateKey of(List<KmipDataType> values) {
    var builder = PrivateKey.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(KeyBlock.kmipTag)) {
      builder.keyBlock((KeyBlock) map
          .get(KeyBlock.kmipTag)
          .getFirst());
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(keyBlock, "KeyBlock cannot be null");
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
        .of(keyBlock)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public ObjectType getObjectType() {
    return objectTypeValue.inst();
  }
}
