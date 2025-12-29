package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("MachineIdentifier Domain Tests")
class MachineIdentifierTest extends AbstractKmipDataTypeSuite<MachineIdentifier> {

    @Override
    protected Class<MachineIdentifier> type() {
        return MachineIdentifier.class;
    }

    @Override
    protected MachineIdentifier createDefault() {
        return MachineIdentifier.builder().value("test-machine-id").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}