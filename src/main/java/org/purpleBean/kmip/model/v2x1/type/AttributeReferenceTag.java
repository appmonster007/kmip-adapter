package org.purpleBean.kmip.model.v2x1.type;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;

/**
 * Represents an AttributeReference encoded as an ENUMERATION — references a standard attribute
 * by its tag.
 * Used in v2.1 GetAttributes request payloads.
 */
@Data
@Builder(toBuilder = true)
public class AttributeReferenceTag implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE_REFERENCE.inst();
  public static final EncodingType encodingType = EncodingType.ENUMERATION;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeReferenceTag.class);
    }
  }

  @NonNull
  private final String tagDescription;

  @Builder
  private AttributeReferenceTag(@NonNull String tagDescription) {
    this.tagDescription = tagDescription;
    validate();
  }

  public static AttributeReferenceTag of(@NonNull String tagDescription) {
    return new AttributeReferenceTag(tagDescription);
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
    return supportedVersions.contains(KmipContext.getSpec());
  }

  /**
   * Returns a KmipEnumeration.Value whose description is the attribute tag description ("Name",
   * etc.).
   * Used by the XML serializer which calls getDescription() on ENUMERATION values.
   */
  @Override
  public Object getValue() {
    return new KmipEnumeration.Value<AttributeReferenceTag>() {
      @Override
      public int getValue() {
        return KmipTag
            .fromName(tagDescription)
            .getValue();
      }

      @Override
      public String getDescription() {
        return tagDescription;
      }

      @Override
      public boolean isSupported() {
        return true;
      }

      @Override
      public boolean isCustom() {
        return false;
      }

      @Override
      public AttributeReferenceTag inst() {
        return AttributeReferenceTag.this;
      }
    };
  }
}
