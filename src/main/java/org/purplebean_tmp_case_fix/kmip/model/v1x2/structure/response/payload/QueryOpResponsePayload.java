package org.purplebean.kmip.model.v1x2.structure.response.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.ExtensionInformation;
import org.purplebean.kmip.model.core.structure.ServerInformation;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;
import org.purplebean.kmip.model.core.type.VendorIdentification;

@Data
@Builder(toBuilder = true)
public class QueryOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.QUERY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, QueryOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, QueryOpResponsePayload.class,
          QueryOpResponsePayload::of);
    }
  }

  @Singular
  private final List<Operation> operations;

  @Singular
  private final List<ObjectType> objectTypes;

  private final VendorIdentification vendorIdentification;

  private final ServerInformation serverInformation;

  @Singular
  private final List<ApplicationNamespace> applicationNamespaces;

  @Singular
  private final List<ExtensionInformation> extensionInformations;

  @Singular
  private final List<AttestationType> attestationTypes;

  @Builder
  private QueryOpResponsePayload(
      List<Operation> operations,
      List<ObjectType> objectTypes,
      VendorIdentification vendorIdentification,
      ServerInformation serverInformation,
      List<ApplicationNamespace> applicationNamespaces,
      List<ExtensionInformation> extensionInformations,
      List<AttestationType> attestationTypes
  ) {
    this.operations = operations;
    this.objectTypes = objectTypes;
    this.vendorIdentification = vendorIdentification;
    this.serverInformation = serverInformation;
    this.applicationNamespaces = applicationNamespaces;
    this.extensionInformations = extensionInformations;
    this.attestationTypes = attestationTypes;
    validate();
  }

  public static QueryOpResponsePayload of(List<KmipDataType> values) {
    var builder = QueryOpResponsePayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(Operation.kmipTag)) {
      map
          .get(Operation.kmipTag)
          .forEach(item -> builder.operation((Operation) item));
    }
    if (map.containsKey(ObjectType.kmipTag)) {
      map
          .get(ObjectType.kmipTag)
          .forEach(item -> builder.objectType((ObjectType) item));
    }
    if (map.containsKey(VendorIdentification.kmipTag)) {
      builder.vendorIdentification((VendorIdentification) map
          .get(VendorIdentification.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ServerInformation.kmipTag)) {
      builder.serverInformation((ServerInformation) map
          .get(ServerInformation.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ApplicationNamespace.kmipTag)) {
      map
          .get(ApplicationNamespace.kmipTag)
          .forEach(item -> builder.applicationNamespace((ApplicationNamespace) item));
    }
    if (map.containsKey(ExtensionInformation.kmipTag)) {
      map
          .get(ExtensionInformation.kmipTag)
          .forEach(item -> builder.extensionInformation((ExtensionInformation) item));
    }
    if (map.containsKey(AttestationType.kmipTag)) {
      map
          .get(AttestationType.kmipTag)
          .forEach(item -> builder.attestationType((AttestationType) item));
    }
    return builder.build();
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
        .of(
            operations,
            objectTypes,
            vendorIdentification,
            serverInformation,
            applicationNamespaces,
            extensionInformations,
            attestationTypes)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
