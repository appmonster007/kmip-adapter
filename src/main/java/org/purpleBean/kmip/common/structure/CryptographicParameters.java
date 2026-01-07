package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP CryptographicParameters attribute structure.
 *
 * <p>Represents a CryptographicParameters in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class CryptographicParameters implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CryptographicParameters.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CryptographicParameters.class, CryptographicParameters::of);
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

    public static CryptographicParameters of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValue().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return CryptographicParameters.builder()
                .blockCipherMode((BlockCipherMode) map.get(BlockCipherMode.kmipTag).get(0))
                .paddingMethod((PaddingMethod) map.get(PaddingMethod.kmipTag).get(0))
                .hashingAlgorithm((HashingAlgorithm) map.get(HashingAlgorithm.kmipTag).get(0))
                .keyRoleType((KeyRoleType) map.get(KeyRoleType.kmipTag).get(0))
                .digitalSignatureAlgorithm((DigitalSignatureAlgorithm) map.get(DigitalSignatureAlgorithm.kmipTag).get(0))
                .cryptographicAlgorithm((CryptographicAlgorithm) map.get(CryptographicAlgorithm.kmipTag).get(0))
                .randomIv((RandomIv) map.get(RandomIv.kmipTag).get(0))
                .ivLength((IvLength) map.get(IvLength.kmipTag).get(0))
                .tagLength((TagLength) map.get(TagLength.kmipTag).get(0))
                .fixedFieldLength((FixedFieldLength) map.get(FixedFieldLength.kmipTag).get(0))
                .invocationFieldLength((InvocationFieldLength) map.get(InvocationFieldLength.kmipTag).get(0))
                .counterLength((CounterLength) map.get(CounterLength.kmipTag).get(0))
                .initialCounterValue((InitialCounterValue) map.get(InitialCounterValue.kmipTag).get(0))
                .build();
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
    public List<KmipDataType> getValues() {
        return Stream.of(
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
        ).filter(Objects::nonNull).toList();
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && getValues().stream().allMatch(KmipDataType::isSupported);
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
        return getAttributeName().getValue();
    }

    @Override
    public AttributeValue.Value getAttributeValue() {
        return AttributeValue.Structure.of(getValues());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    public static class CryptographicParametersBuilder {
        public CryptographicParameters build() {
            validate();
            return new CryptographicParameters(
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
            );
        }

        private void validate() {

        }
    }
}