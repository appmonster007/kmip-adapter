package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.type.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class DeviceCredential implements CredentialValue, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DeviceCredential.class);
            CredentialValue.register(spec, encodingType, CredentialType.Standard.DEVICE, DeviceCredential.class, DeviceCredential::of);
        }
    }

    private final DeviceSerialNumber deviceSerialNumber;
    private final Password password;
    private final DeviceIdentifier deviceIdentifier;
    private final NetworkIdentifier networkIdentifier;
    private final MachineIdentifier machineIdentifier;
    private final MediaIdentifier mediaIdentifier;

    @Builder
    private DeviceCredential(
            DeviceSerialNumber deviceSerialNumber,
            Password password,
            DeviceIdentifier deviceIdentifier,
            NetworkIdentifier networkIdentifier,
            MachineIdentifier machineIdentifier,
            MediaIdentifier mediaIdentifier
    ) {
        this.deviceSerialNumber = deviceSerialNumber;
        this.password = password;
        this.deviceIdentifier = deviceIdentifier;
        this.networkIdentifier = networkIdentifier;
        this.machineIdentifier = machineIdentifier;
        this.mediaIdentifier = mediaIdentifier;
        validate();
    }

    public static DeviceCredential of(CredentialValue value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid credential value: " + value);
        }
        return of(structure.getValues());
    }

    public static DeviceCredential of(KmipDataType... values) {
        return of(List.of(values));
    }

    public static DeviceCredential of(List<KmipDataType> values) {
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        DeviceCredentialBuilder builder = DeviceCredential.builder();
        if (map.containsKey(DeviceSerialNumber.kmipTag)) {
            builder.deviceSerialNumber((DeviceSerialNumber) map.get(DeviceSerialNumber.kmipTag).getFirst());
        }
        if (map.containsKey(Password.kmipTag)) {
            builder.password((Password) map.get(Password.kmipTag).getFirst());
        }
        if (map.containsKey(DeviceIdentifier.kmipTag)) {
            builder.deviceIdentifier((DeviceIdentifier) map.get(DeviceIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(NetworkIdentifier.kmipTag)) {
            builder.networkIdentifier((NetworkIdentifier) map.get(NetworkIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(MachineIdentifier.kmipTag)) {
            builder.machineIdentifier((MachineIdentifier) map.get(MachineIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(MediaIdentifier.kmipTag)) {
            builder.mediaIdentifier((MediaIdentifier) map.get(MediaIdentifier.kmipTag).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation required for this structure
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        deviceSerialNumber,
                        password,
                        deviceIdentifier,
                        networkIdentifier,
                        machineIdentifier,
                        mediaIdentifier)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}