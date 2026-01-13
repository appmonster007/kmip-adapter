package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MachineIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MachineIdentifier TTLV Serialization Tests")
class MachineIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<MachineIdentifier> {

    @Override
    protected Class<MachineIdentifier> type() {
        return MachineIdentifier.class;
    }

    @Override
    protected MachineIdentifier createDefault() {
        return MachineIdentifier.builder().value("test-machine-id").build();
    }

    @Override
    protected MachineIdentifier createVariant() {
        return MachineIdentifier.builder().value("another-machine-id").build();
    }
}