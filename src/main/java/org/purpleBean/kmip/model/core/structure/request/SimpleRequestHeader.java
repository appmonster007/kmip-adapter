package org.purpleBean.kmip.model.core.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.purpleBean.kmip.api.KmipTag.Standard.PROTOCOL_VERSION;

@Data
@Builder(toBuilder = true)
public class SimpleRequestHeader implements RequestHeaderStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

    static {
        KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType, SimpleRequestHeader.class);
        RequestHeaderStructure.register(KmipSpec.UnknownVersion, SimpleRequestHeader.class, SimpleRequestHeader::of);
    }

    @NonNull
    private final ProtocolVersion protocolVersion;

    @Builder
    private SimpleRequestHeader(@NonNull ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
        validate();
    }

    public static SimpleRequestHeader of(KmipDataType... values) {
        return of(List.of(values));
    }

    public static SimpleRequestHeader of(List<KmipDataType> values) {
        var builder = SimpleRequestHeader.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(PROTOCOL_VERSION.inst())) {
            builder.protocolVersion((ProtocolVersion) map.get(PROTOCOL_VERSION.inst()).get(0));
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
    public List<KmipDataType> getValue() {
        return Stream.of(protocolVersion)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSupported() {
        return supportedVersions.contains(KmipContext.getSpec());
    }
}