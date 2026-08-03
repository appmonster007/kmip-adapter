package org.purplebean.kmip.model.core.type;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP {@code UniqueIdentifier} dataType ({@code 0x420094}, KMIP §5 / 3.0 §4.68).
 *
 * <p>Polymorphic across wire encodings — TextString (v1.2+, the default form), Integer (v2.1+
 * batch-index reference) and, in KMIP 3.0 (§4.68), Identifier/Reference/NameReference. All of
 * these share the same underlying character/string shape and are represented by this single
 * class, with {@link #sourceEncoding} tracking which wire variant was actually parsed so that
 * round-trip (de)serialization preserves it.
 *
 * <p>The Enumeration variant (v2.1+ ID-Placeholder / batch-item references) is NOT represented here
 * — it is owned exclusively by {@link org.purplebean.kmip.model.v2x1.enumeration.UniqueIdentifier}
 * (a proper {@link org.purplebean.kmip.api.KmipEnumeration}), to avoid a registry collision on
 * {@code (tag=UNIQUE_IDENTIFIER, encoding=ENUMERATION)}.
 *
 * @see org.purplebean.kmip.model.v2x1.enumeration.UniqueIdentifier
 */
@Data
@Builder(toBuilder = true)
public class UniqueIdentifier implements KmipDataType, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.UNIQUE_IDENTIFIER.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      // Registered under every wire encoding it may appear as (§5 / KMIP 3.0 §4.68) so that
      // polymorphic dispatch (e.g. inside an Attributes wrapper) resolves regardless of which
      // variant is on the wire, not just the class-level default (TextString).
      // NOTE: ENUMERATION is intentionally excluded — that variant is owned exclusively by
      // model.v2x1.enumeration.UniqueIdentifier (proper KmipEnumeration semantics for
      // "IDPlaceholder" etc.); registering it here too would collide on the same registry key.
      for (EncodingType variant : new EncodingType[] {encodingType, EncodingType.INTEGER,
          EncodingType.IDENTIFIER, EncodingType.REFERENCE, EncodingType.NAME_REFERENCE}) {
        KmipDataType.register(spec, kmipTag.getValue(), variant, UniqueIdentifier.class);
        KmipAttribute.register(spec, kmipTag.getValue(), variant, UniqueIdentifier.class,
            UniqueIdentifier::of);
      }
    }
  }

  @NonNull
  private final String value;

  // KMIP §5 UniqueIdentifier may appear as TextString, Integer (v2.1+ batch-index reference), or,
  // in KMIP 3.0 (§4.68), Identifier/Reference/NameReference. sourceEncoding tracks the wire
  // encoding
  // so round-trip serialization preserves the original type. Excluded from equals/hashCode: it's
  // wire-format bookkeeping, not part of the value's identity.
  @EqualsAndHashCode.Exclude
  private final EncodingType sourceEncoding;

  @Builder
  private UniqueIdentifier(@NonNull String value, EncodingType sourceEncoding) {
    this.value = value;
    this.sourceEncoding = sourceEncoding;
    validate();
  }

  /**
   * Returns the {@link UniqueIdentifier} instance wrapping the given value.
   */
  public static UniqueIdentifier of(@NonNull String value) {
    return new UniqueIdentifier(value, null);
  }

  /**
   * Returns the {@link UniqueIdentifier} instance wrapping the given value.
   */
  public static UniqueIdentifier of(@NonNull AttributeName attributeName,
                                    @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof String value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return UniqueIdentifier
        .builder()
        .value(value)
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
    return sourceEncoding != null ? sourceEncoding : encodingType;
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec);
  }

  @Override
  public boolean isAlwaysPresent() {
    return true;
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
    return false;
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
}
