package org.purpleBean.kmip.model.v2x1.type;

import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.util.StringUtils;

/**
 * KMIP CertificateIssuerOu datatype attribute.
 */
@Data
@Builder(toBuilder = true)
public class CertificateIssuerOu implements KmipDataType, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.CERTIFICATE_ISSUER_OU.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);
      // introduced in KMIP 2.1 — extend if present in later specs

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertificateIssuerOu.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CertificateIssuerOu.class,
          CertificateIssuerOu::of);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private CertificateIssuerOu(@NonNull String value) {
    this.value = value;
    validate();
  }

  public static CertificateIssuerOu of(@NonNull String value) {
    return new CertificateIssuerOu(value);
  }

  public static CertificateIssuerOu of(@NonNull AttributeName attributeName,
                                       @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof String value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return new CertificateIssuerOu(value);
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // No validation needed for this structure
  }

  @Override
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofTextString(value);
  }

  @Override
  public AttributeName getAttributeName() {
    return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
  }

  @Override
  public String getCanonicalName() {
    return kmipTag.getDescription();
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
    return supportedVersions.contains(spec);
  }

  @Override
  public boolean isAlwaysPresent() {
    return false; // TODO: Adjust as needed
  }

  @Override
  public boolean isServerInitializable() {
    return true; // TODO: Adjust as needed
  }

  @Override
  public boolean isClientInitializable() {
    return true; // TODO: Adjust as needed
  }

  @Override
  public boolean isServerModifiable(@NonNull State state) {
    // PRE_ACTIVE is modifiable by default, adjust as needed
    return state.equals(State.Standard.PRE_ACTIVE.inst()); // TODO: Adjust as needed
  }

  @Override
  public boolean isClientModifiable(@NonNull State state) {
    // PRE_ACTIVE is modifiable by default, adjust as needed
    return state.equals(State.Standard.PRE_ACTIVE.inst()); // TODO: Adjust as needed
  }

  @Override
  public boolean isClientDeletable() {
    return false; // TODO: Adjust as needed
  }

  @Override
  public boolean isMultiInstanceAllowed() {
    return false; // TODO: Adjust as needed
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertificateIssuerOu that = (CertificateIssuerOu) o;
    // TODO: Adjust equals for specific data type if needed (e.g., OffsetDateTime.withNano(0)
    //  .atZoneSameInstant(ZoneOffset.UTC))
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    // TODO: Adjust hashCode for specific data type if needed (e.g., OffsetDateTime.withNano(0)
    //  .atZoneSameInstant(ZoneOffset.UTC))
    return Objects.hash(value);
  }
}