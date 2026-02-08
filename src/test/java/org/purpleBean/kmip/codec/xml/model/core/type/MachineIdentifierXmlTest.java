package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MachineIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MachineIdentifier XML Serialization Tests")
class MachineIdentifierXmlTest extends AbstractXmlSerializationTestSuite<MachineIdentifier> {

    @Override
    public Class<MachineIdentifier> type() {
        return MachineIdentifier.class;
    }

    @Override
    public MachineIdentifier createDefault() {
        return MachineIdentifier.builder().value("test-machine-id").build();
    }

    @Override
    public MachineIdentifier createVariant() {
        return MachineIdentifier.builder().value("another-machine-id").build();
    }
}