package org.purpleBean.kmip.model.v2x1.structure;

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
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

@Data
@Builder(toBuilder = true)
public class Attribute implements KmipStructure, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Attribute.class);
      KmipAttribute.ATTRIBUTE_REGISTRY.put(
          new KmipAttribute.RegistryKey(spec, kmipTag.getValue(), encodingType), Attribute.class);
    }
  }

  @NonNull
  private final VendorIdentification vendorIdentification;
  @NonNull
  private final AttributeName attributeName;
  private final AttributeValue attributeValue;

  @Builder
  private Attribute(
      @NonNull VendorIdentification vendorIdentification,
      @NonNull AttributeName attributeName,
      AttributeValue attributeValue
  ) {
    this.vendorIdentification = vendorIdentification;
    this.attributeName = attributeName;
    this.attributeValue = attributeValue;
    validate();
  }

  public static Attribute of(List<KmipDataType> values) {
    var builder = Attribute.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(VendorIdentification.kmipTag)) {
      builder.vendorIdentification((VendorIdentification) map
          .get(VendorIdentification.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AttributeName.kmipTag)) {
      builder.attributeName((AttributeName) map
          .get(AttributeName.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AttributeValue.kmipTag)) {
      builder.attributeValue((AttributeValue) map
          .get(AttributeValue.kmipTag)
          .getFirst());
    }
    return builder.build();
  }

  @Override
  public boolean isAlwaysPresent() {
    return false;
  }

  @Override
  public boolean isServerInitializable() {
    return false;
  }

  @Override
  public boolean isClientInitializable() {
    return true;
  }

  @Override
  public boolean isServerModifiable(org.purpleBean.kmip.model.core.enumeration.State state) {
    return true;
  }

  @Override
  public boolean isClientModifiable(org.purpleBean.kmip.model.core.enumeration.State state) {
    return true;
  }

  @Override
  public boolean isClientDeletable() {
    return true;
  }

  @Override
  public boolean isMultiInstanceAllowed() {
    return true;
  }

  @Override
  public AttributeValue getAttributeValue() {
    return attributeValue;
  }

  @Override
  public AttributeName getAttributeName() {
    return attributeName;
  }

  @Override
  public String getCanonicalName() {
    return attributeName != null ? attributeName.getValue() : kmipTag.getDescription();
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
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(vendorIdentification, attributeName, attributeValue)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
