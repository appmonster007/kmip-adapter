package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeSuite;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("UsageLimits Domain Tests")
class UsageLimitsTest extends AbstractKmipStructureAttributeSuite<UsageLimits> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<UsageLimits> type() {
        return UsageLimits.class;
    }

    @Override
    protected UsageLimits createDefault() {
        return UsageLimits.builder()
                .usageLimitsTotal(UsageLimitsTotal.of(100L))
                .usageLimitsCount(UsageLimitsCount.of(10L))
                .usageLimitsUnit(new UsageLimitsUnit(UsageLimitsUnit.Standard.BYTE))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 3;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values.get(0)).isInstanceOf(UsageLimitsTotal.class);
        assertThat(values.get(1)).isInstanceOf(UsageLimitsCount.class);
        assertThat(values.get(2)).isInstanceOf(UsageLimitsUnit.class);
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
        return true;
    }

    @Override
    protected boolean expectClientDeletable() {
        return true;
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
