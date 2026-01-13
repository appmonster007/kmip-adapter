package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeTestSuite;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("CryptographicParameters Domain Tests")
class CryptographicParametersTest extends AbstractKmipStructureAttributeTestSuite<CryptographicParameters> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CryptographicParameters> type() {
        return CryptographicParameters.class;
    }

    @Override
    protected CryptographicParameters createDefault() {
        return CryptographicParameters.builder()
                .blockCipherMode(new BlockCipherMode(BlockCipherMode.Standard.CBC))
                .paddingMethod(new PaddingMethod(PaddingMethod.Standard.PKCS5))
                .hashingAlgorithm(new HashingAlgorithm(HashingAlgorithm.Standard.SHA_256))
                .keyRoleType(new KeyRoleType(KeyRoleType.Standard.KEK))
                .digitalSignatureAlgorithm(new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.Standard.SHA_1_WITH_RSA_ENCRYPTION))
                .cryptographicAlgorithm(new CryptographicAlgorithm(CryptographicAlgorithm.Standard.AES))
                .randomIv(RandomIv.of(true))
                .ivLength(IvLength.of(128))
                .tagLength(TagLength.of(128))
                .fixedFieldLength(FixedFieldLength.of(128))
                .invocationFieldLength(InvocationFieldLength.of(128))
                .counterLength(CounterLength.of(128))
                .initialCounterValue(InitialCounterValue.of(1))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 13;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values.get(0)).isInstanceOf(BlockCipherMode.class);
        assertThat(values.get(1)).isInstanceOf(PaddingMethod.class);
        assertThat(values.get(2)).isInstanceOf(HashingAlgorithm.class);
        assertThat(values.get(3)).isInstanceOf(KeyRoleType.class);
        assertThat(values.get(4)).isInstanceOf(DigitalSignatureAlgorithm.class);
        assertThat(values.get(5)).isInstanceOf(CryptographicAlgorithm.class);
        assertThat(values.get(6)).isInstanceOf(RandomIv.class);
        assertThat(values.get(7)).isInstanceOf(IvLength.class);
        assertThat(values.get(8)).isInstanceOf(TagLength.class);
        assertThat(values.get(9)).isInstanceOf(FixedFieldLength.class);
        assertThat(values.get(10)).isInstanceOf(InvocationFieldLength.class);
        assertThat(values.get(11)).isInstanceOf(CounterLength.class);
        assertThat(values.get(12)).isInstanceOf(InitialCounterValue.class);
    }

    @Override
    protected boolean expectAlwaysPresent() {
        return false;
    }

    @Override
    protected boolean expectServerInitializable() {
        return true;
    }

    @Override
    protected boolean expectClientInitializable() {
        return false;
    }

    @Override
    protected boolean expectClientDeletable() {
        return false;
    }

    @Override
    protected boolean expectMultiInstanceAllowed() {
        return true;
    }

    @Override
    protected State stateForServerModifiableTrue() {
        return null;
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return null;
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return null;
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return null;
    }

    @Override
    protected void attrStruct_serverModifiable_respectsState() {
    }

    @Override
    protected void attrStruct_clientModifiable_respectsState() {
    }
}