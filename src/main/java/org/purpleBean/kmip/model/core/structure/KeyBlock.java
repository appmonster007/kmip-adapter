package org.purpleBean.kmip.model.core.structure;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KeyValue;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

@Data
@Builder(toBuilder = true)
public class KeyBlock implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.KEY_BLOCK.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyBlock.class);
    }
  }

  @NonNull
  private final KeyFormatType keyFormatType;

  private final KeyCompressionType keyCompressionType;

  private final KeyValue keyValue;

  private final CryptographicAlgorithm cryptographicAlgorithm;

  private final CryptographicLength cryptographicLength;

  private final KeyWrappingData keyWrappingData;

  @Builder
  private KeyBlock(
      @NonNull KeyFormatType keyFormatType,
      KeyCompressionType keyCompressionType,
      KeyValue keyValue,
      CryptographicAlgorithm cryptographicAlgorithm,
      CryptographicLength cryptographicLength,
      KeyWrappingData keyWrappingData
  ) {
    this.keyFormatType = keyFormatType;
    this.keyCompressionType = keyCompressionType;
    this.keyValue = keyValue;
    this.cryptographicAlgorithm = cryptographicAlgorithm;
    this.cryptographicLength = cryptographicLength;
    this.keyWrappingData = keyWrappingData;
    validate();
  }

  public static KeyBlock of(
      @NonNull KeyFormatType keyFormatType,
      KeyCompressionType keyCompressionType,
      KeyValue keyValue,
      CryptographicAlgorithm cryptographicAlgorithm,
      CryptographicLength cryptographicLength,
      KeyWrappingData keyWrappingData
  ) {
    return KeyBlock
        .builder()
        .keyFormatType(keyFormatType)
        .keyCompressionType(keyCompressionType)
        .keyValue(keyValue)
        .cryptographicAlgorithm(cryptographicAlgorithm)
        .cryptographicLength(cryptographicLength)
        .keyWrappingData(keyWrappingData)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(keyFormatType, "KeyFormatType cannot be null");
    if (cryptographicAlgorithm != null && cryptographicLength == null) {
      throw new IllegalStateException(
          "CryptographicLength must be present if CryptographicAlgorithm is present");
    }
    if (cryptographicAlgorithm == null && cryptographicLength != null) {
      throw new IllegalStateException(
          "CryptographicAlgorithm must be present if CryptographicLength is present");
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
            keyFormatType,
            keyCompressionType,
            keyValue,
            cryptographicAlgorithm,
            cryptographicLength,
            keyWrappingData)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
