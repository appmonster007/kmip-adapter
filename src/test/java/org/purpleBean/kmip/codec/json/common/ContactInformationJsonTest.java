package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ContactInformation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ContactInformation JSON Serialization Tests")
class ContactInformationJsonTest extends AbstractJsonSerializationSuite<ContactInformation> {


    @Override
    protected Class<ContactInformation> type() {
        return ContactInformation.class;
    }

    @Override
    protected ContactInformation createDefault() {
        return ContactInformation.builder().value("test").build();
    }

    @Override
    protected ContactInformation createVariant() {
        return ContactInformation.builder().value("test-2").build();
    }
}
