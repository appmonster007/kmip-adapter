package org.purpleBean.kmip.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

@DisplayName("SimpleRequestHeader Structure Semantics")
class SimpleRequestHeaderTest extends AbstractKmipStructureTestSuite<SimpleRequestHeader> {

    @Override
    protected Class<SimpleRequestHeader> type() {
        return SimpleRequestHeader.class;
    }

    @Override
    protected SimpleRequestHeader createDefault() {
        return SimpleRequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .build();
    }

    @Override
    protected int expectedMinComponentCount() {
        return 1; // header exposes protocolVersion as a component
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // first element should be ProtocolVersion
        assert values.get(0) instanceof ProtocolVersion;
    }

    @Override
    protected boolean expectedSupportedForUnsupportedSpec() {
        return true; // current impl supports all specs
    }
}
