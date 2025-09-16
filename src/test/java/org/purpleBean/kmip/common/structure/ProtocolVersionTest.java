package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.util.List;

@DisplayName("ProtocolVersion Structure Semantics")
class ProtocolVersionTest extends AbstractKmipStructureSuite<ProtocolVersion> {

    @Override
    protected Class<ProtocolVersion> type() {
        return ProtocolVersion.class;
    }

    @Override
    protected ProtocolVersion createDefault() {
        return ProtocolVersion.of(1, 2);
    }

    @Override
    protected int expectedMinComponentCount() {
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // ProtocolVersion exposes major, minor as two components
        // No deep asserts here to keep this reusable; specific tests can subclass and add more
    }

    @Override
    protected boolean expectedSupportedForUnsupportedSpec() {
        return true;
    }
}
