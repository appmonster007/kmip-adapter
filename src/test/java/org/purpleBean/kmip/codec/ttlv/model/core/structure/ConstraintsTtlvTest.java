package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Constraints;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.Collections;

@DisplayName("Constraints Ttlv Serialization Tests")
class ConstraintsTtlvTest extends AbstractTtlvSerializationTestSuite<Constraints> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<Constraints> type() {
        return Constraints.class;
    }

    @Override
    public Constraints createDefault() {
        return Constraints.of(Collections.emptyList());
    }

    @Override
    public Constraints createVariant() {
        return Constraints.of(Collections.emptyList());
    }
}
