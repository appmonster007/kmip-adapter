package org.purpleBean.kmip.model.core.structure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

@Data
@Builder(toBuilder = true)
public class SecretData implements ManagedObject, KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.SECRET_DATA.inst();
  public static final ObjectType.Value objectTypeValue = ObjectType.Standard.SECRET_DATA;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, SecretData.class);
      ManagedObject.register(spec, kmipTag.getValue(), encodingType, objectTypeValue,
          SecretData.class, SecretData::of);
    }
  }

  @NonNull
  private final SecretDataType secretDataType;

  @NonNull
  private final KeyBlock keyBlock;

  @Builder
  private SecretData(
      @NonNull SecretDataType secretDataType,
      @NonNull KeyBlock keyBlock
  ) {
    this.secretDataType = secretDataType;
    this.keyBlock = keyBlock;
    validate();
  }

  public static SecretData of(
      @NonNull SecretDataType secretDataType,
      @NonNull KeyBlock keyBlock
  ) {
    return SecretData
        .builder()
        .secretDataType(secretDataType)
        .keyBlock(keyBlock)
        .build();
  }

  public static SecretData of(List<KmipDataType> values) {
    var builder = SecretData.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(SecretDataType.kmipTag)) {
      builder.secretDataType((SecretDataType) map
          .get(SecretDataType.kmipTag)
          .getFirst());
    }
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
    Objects.requireNonNull(secretDataType, "SecretDataType cannot be null");
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
        .of(secretDataType, keyBlock)
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
