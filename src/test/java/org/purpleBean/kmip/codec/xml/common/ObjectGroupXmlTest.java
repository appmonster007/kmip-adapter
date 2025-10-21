package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ObjectGroup;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ObjectGroup XML Serialization Tests")
class ObjectGroupXmlTest extends AbstractXmlSerializationSuite<ObjectGroup> {

    @Override
    protected Class<ObjectGroup> type() {
        return ObjectGroup.class;
    }

    @Override
    protected ObjectGroup createDefault() {
        // TODO: Update with actual default values for your dataType
        return ObjectGroup.builder().value("test").build();
    }

    @Override
    protected ObjectGroup createVariant() {
        // TODO: Update with different values to test variations
        return ObjectGroup.builder().value("test-2").build();
    }
}
