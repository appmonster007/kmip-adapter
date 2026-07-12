package org.purpleBean.kmip.codec.json.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.structure.Constraints;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.Collections;

@DisplayName("Constraints Json Serialization Tests")
class ConstraintsJsonTest extends AbstractJsonSerializationTestSuite<Constraints> {

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
