package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ObjectGroup;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObjectGroup XML Serialization Tests")
class ObjectGroupXmlTest extends AbstractXmlSerializationTestSuite<ObjectGroup> {

    @Override
    protected Class<ObjectGroup> type() {
        return ObjectGroup.class;
    }

    @Override
    protected ObjectGroup createDefault() {

        return ObjectGroup.builder().value("test").build();
    }

    @Override
    protected ObjectGroup createVariant() {

        return ObjectGroup.builder().value("test-2").build();
    }
}
