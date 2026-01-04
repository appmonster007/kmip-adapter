package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeSuite;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("RevocationReason Domain Tests")
class RevocationReasonTest extends AbstractKmipStructureAttributeSuite<RevocationReason> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<RevocationReason> type() {
        return RevocationReason.class;
    }

    @Override
    protected RevocationReason createDefault() {
        return RevocationReason.builder()
                .revocationReasonCode(new RevocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE))
                .revocationMessage(RevocationMessage.of("test-message"))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 1;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values.get(0)).isInstanceOf(RevocationReasonCode.class);
        assertThat(values.get(1)).isInstanceOf(RevocationMessage.class);
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
        return false;
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
