package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ObjectGroup;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ObjectGroup JSON Serialization Tests")
class ObjectGroupJsonTest extends AbstractJsonSerializationSuite<ObjectGroup> {

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
