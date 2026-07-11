package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.Attributes;
import org.purpleBean.kmip.model.core.structure.ObjectDefaults;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Collections;

@DisplayName("ObjectDefaults Xml Serialization Tests")
class ObjectDefaultsXmlTest extends AbstractXmlSerializationTestSuite<ObjectDefaults> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<ObjectDefaults> type() {
        return ObjectDefaults.class;
    }

    @Override
    public ObjectDefaults createDefault() {
        return ObjectDefaults.of(
                ObjectType.Standard.SYMMETRIC_KEY.inst(),
                Attributes.of(Collections.emptyList())
        );
    }

    @Override
    public ObjectDefaults createVariant() {
        return ObjectDefaults.of(
                ObjectType.Standard.CERTIFICATE.inst(),
                Attributes.of(Collections.emptyList())
        );
    }
}
