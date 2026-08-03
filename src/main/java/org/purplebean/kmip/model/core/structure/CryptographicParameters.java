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
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyRoleType;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.CounterLength;
import org.purplebean.kmip.model.core.type.FixedFieldLength;
import org.purplebean.kmip.model.core.type.InitialCounterValue;
import org.purplebean.kmip.model.core.type.InvocationFieldLength;
import org.purplebean.kmip.model.core.type.IvLength;
import org.purplebean.kmip.model.core.type.RandomIv;
import org.purplebean.kmip.model.core.type.TagLength;
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP CryptographicParameters attribute structure.
 *
 * <p>Represents a CryptographicParameters in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class CryptographicParameters implements KmipStructure, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, CryptographicParameters.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CryptographicParameters.class,
          CryptographicParameters::of);
    }
  }

  private final BlockCipherMode blockCipherMode;
  private final PaddingMethod paddingMethod;
  private final HashingAlgorithm hashingAlgorithm;
  private final KeyRoleType keyRoleType;
  private final DigitalSignatureAlgorithm digitalSignatureAlgorithm;
  private final CryptographicAlgorithm cryptographicAlgorithm;
  private final RandomIv randomIv;
  private final IvLength ivLength;
  private final TagLength tagLength;
  private final FixedFieldLength fixedFieldLength;
  private final InvocationFieldLength invocationFieldLength;
  private final CounterLength counterLength;
  private final InitialCounterValue initialCounterValue;

  @Builder
  private CryptographicParameters(
      BlockCipherMode blockCipherMode,
      PaddingMethod paddingMethod,
      HashingAlgorithm hashingAlgorithm,
      KeyRoleType keyRoleType,
      DigitalSignatureAlgorithm digitalSignatureAlgorithm,
      CryptographicAlgorithm cryptographicAlgorithm,
      RandomIv randomIv,
      IvLength ivLength,
      TagLength tagLength,
      FixedFieldLength fixedFieldLength,
      InvocationFieldLength invocationFieldLength,
      CounterLength counterLength,
      InitialCounterValue initialCounterValue
  ) {
    this.blockCipherMode = blockCipherMode;
    this.paddingMethod = paddingMethod;
    this.hashingAlgorithm = hashingAlgorithm;
    this.keyRoleType = keyRoleType;
    this.digitalSignatureAlgorithm = digitalSignatureAlgorithm;
    this.cryptographicAlgorithm = cryptographicAlgorithm;
    this.randomIv = randomIv;
    this.ivLength = ivLength;
    this.tagLength = tagLength;
    this.fixedFieldLength = fixedFieldLength;
    this.invocationFieldLength = invocationFieldLength;
    this.counterLength = counterLength;
    this.initialCounterValue = initialCounterValue;
    validate();
  }

  /**
   * Returns the {@link CryptographicParameters} instance wrapping the given value.
   */
  public static CryptographicParameters of(@NonNull AttributeName attributeName,
                                           @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType
        || !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure)
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return CryptographicParameters
        .builder()
        .blockCipherMode(map.containsKey(BlockCipherMode.kmipTag) ? (BlockCipherMode) map
            .get(BlockCipherMode.kmipTag)
            .get(0) : null)
        .paddingMethod(map.containsKey(PaddingMethod.kmipTag) ? (PaddingMethod) map
            .get(PaddingMethod.kmipTag)
            .get(0) : null)
        .hashingAlgorithm(map.containsKey(HashingAlgorithm.kmipTag) ? (HashingAlgorithm) map
            .get(HashingAlgorithm.kmipTag)
            .get(0) : null)
        .keyRoleType(map.containsKey(KeyRoleType.kmipTag) ? (KeyRoleType) map
            .get(KeyRoleType.kmipTag)
            .get(0) : null)
        .digitalSignatureAlgorithm(map.containsKey(DigitalSignatureAlgorithm.kmipTag)
            ? (DigitalSignatureAlgorithm) map
                .get(DigitalSignatureAlgorithm.kmipTag)
                .get(0) : null)
        .cryptographicAlgorithm(map.containsKey(CryptographicAlgorithm.kmipTag)
            ? (CryptographicAlgorithm) map
                .get(CryptographicAlgorithm.kmipTag)
                .get(0) : null)
        .randomIv(map.containsKey(RandomIv.kmipTag) ? (RandomIv) map
            .get(RandomIv.kmipTag)
            .get(0) : null)
        .ivLength(map.containsKey(IvLength.kmipTag) ? (IvLength) map
            .get(IvLength.kmipTag)
            .get(0) : null)
        .tagLength(map.containsKey(TagLength.kmipTag) ? (TagLength) map
            .get(TagLength.kmipTag)
            .get(0) : null)
        .fixedFieldLength(map.containsKey(FixedFieldLength.kmipTag) ? (FixedFieldLength) map
            .get(FixedFieldLength.kmipTag)
            .get(0) : null)
        .invocationFieldLength(map.containsKey(InvocationFieldLength.kmipTag)
            ? (InvocationFieldLength) map
                .get(InvocationFieldLength.kmipTag)
                .get(0) : null)
        .counterLength(map.containsKey(CounterLength.kmipTag) ? (CounterLength) map
            .get(CounterLength.kmipTag)
            .get(0) : null)
        .initialCounterValue(map.containsKey(InitialCounterValue.kmipTag)
            ? (InitialCounterValue) map
                .get(InitialCounterValue.kmipTag)
                .get(0) : null)
        .build();
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
        .of(
            blockCipherMode,
            paddingMethod,
            hashingAlgorithm,
            keyRoleType,
            digitalSignatureAlgorithm,
            cryptographicAlgorithm,
            randomIv,
            ivLength,
            tagLength,
            fixedFieldLength,
            invocationFieldLength,
            counterLength,
            initialCounterValue
        )
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
