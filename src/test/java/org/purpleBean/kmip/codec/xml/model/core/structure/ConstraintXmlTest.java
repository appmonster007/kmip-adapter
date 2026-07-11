package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Constraint;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Collections;

@DisplayName("Constraint Xml Serialization Tests")
class ConstraintXmlTest extends AbstractXmlSerializationTestSuite<Constraint> {

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
