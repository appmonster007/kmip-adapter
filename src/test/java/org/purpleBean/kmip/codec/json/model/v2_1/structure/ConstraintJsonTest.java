package org.purpleBean.kmip.codec.json.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.structure.Constraint;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.Collections;

@DisplayName("Constraint Json Serialization Tests")
class ConstraintJsonTest extends AbstractJsonSerializationTestSuite<Constraint> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<Constraint> type() {
        return Constraint.class;
    }

    @Override
    public Constraint createDefault() {
        return Constraint.of(Collections.emptyList());
    }

    @Override
    public Constraint createVariant() {
        return Constraint.of(Collections.emptyList());
    }
}
