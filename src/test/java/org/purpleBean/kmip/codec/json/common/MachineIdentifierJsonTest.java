package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MachineIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("MachineIdentifier JSON Serialization Tests")
class MachineIdentifierJsonTest extends AbstractJsonSerializationSuite<MachineIdentifier> {

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