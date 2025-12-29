package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MachineIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("MachineIdentifier XML Serialization Tests")
class MachineIdentifierXmlTest extends AbstractXmlSerializationSuite<MachineIdentifier> {

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